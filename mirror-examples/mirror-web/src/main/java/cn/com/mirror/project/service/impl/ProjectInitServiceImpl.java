package cn.com.mirror.project.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.project.dao.entity.Project;
import cn.com.mirror.project.dao.entity.UserPrjRel;
import cn.com.mirror.project.dao.mapper.ProjectMapper;
import cn.com.mirror.project.dao.mapper.UserPrjRelMapper;
import cn.com.mirror.project.pojo.ArchiveVO;
import cn.com.mirror.project.pojo.ProjectVO;
import cn.com.mirror.project.service.ArchiveService;
import cn.com.mirror.project.service.MaxClientService;
import cn.com.mirror.project.service.ProjectInitService;
import cn.com.mirror.util.RedisKeyUtil;
import cn.com.mirror.util.RedisUtil;
import cn.com.mirror.utils.BeanUtils;
import cn.com.mirror.utils.EncryptUtils;
import cn.com.mirror.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProjectInitServiceImpl implements ProjectInitService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserPrjRelMapper userPrjRelMapper;

    @Autowired
    private MaxClientService maxClientService;
    @Autowired
    private ArchiveService archiveService;


    @Override
    public String getAccessCode(String userId) {

        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("User id can not be null or empty");
        }

        String acsCodeRedisKey = RedisKeyUtil.genPrjAccessCodeKey(userId);
        String accessCode = redisUtil.opGetStrVal(acsCodeRedisKey);
        if (null == accessCode) {
            maxClientService.increaseClientCount();

            String salt = UUIDUtils.randomUUID();
            accessCode = EncryptUtils.sha256Encrypt(userId + salt);

            redisUtil.opSetStrValForOneDay(acsCodeRedisKey, accessCode);
        }
        return accessCode;
    }


    @Override
    @Transactional
    public ProjectVO genProject(String userId, String originalFileName, byte[] content) {
        ProjectVO projectVO = new ProjectVO();

        String prjName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String postfix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        String prjRedisKey = RedisKeyUtil.genPrjKey(userId, prjName);
        if (redisUtil.isExists(prjRedisKey)) {
            // project for userId is already exists
            BeanUtils.copyProperties(projectVO, redisUtil.opGetObjVal(prjRedisKey));
        } else {
            // create a new project
            String accessCode = getAccessCode(userId);

            Project tmProject = genPrj(accessCode, prjName, userId);
            projectMapper.insertSelective(tmProject);
            BeanUtils.copyProperties(projectVO, tmProject);

            ArchiveVO archiveVO = archiveService.nailArchive(prjName,
                    ArchiveTypeEnum.checkAchvType(postfix), content);
            projectVO.setAchvId(archiveVO.getAchvId());

            redisUtil.opSetObjVal(prjRedisKey, projectVO);

            // user uploaded a new project
            UserPrjRel userPrjRel = new UserPrjRel();
            userPrjRel.setPrjId(projectVO.getPrjId());
            userPrjRel.setUserId(userId);
            userPrjRelMapper.insertSelective(userPrjRel);
        }


        return projectVO;
    }

    private Project genPrj(String accessCode, String prjName, String userId) {
        Project tmProject = new Project();
        tmProject.setAccessCode(accessCode);
        tmProject.setName(prjName);
        tmProject.setUserId(userId);
        return tmProject;
    }
}
