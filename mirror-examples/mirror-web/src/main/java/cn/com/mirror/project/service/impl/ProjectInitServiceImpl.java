package cn.com.mirror.project.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.exceptions.UnitException;
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
    public ProjectVO genProject(String accessCode,
                                String userId,
                                String originalFileName,
                                byte[] content) {

        String prjName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String postfix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        String prjRedisKey = RedisKeyUtil.genPrjKey(userId, prjName, accessCode);
        ProjectVO redisPrjVO = redisUtil.opGetObjVal(prjRedisKey, ProjectVO.class);

        ProjectVO projectVO = null;
        if (null == redisPrjVO ||
                (null != redisPrjVO && prjName.equals(redisPrjVO.getName()))) {

            projectVO = newProject(userId, prjName, postfix, prjRedisKey, content);
        } else {
            throw new UnitException("One access code can only upload one project");
        }

//        TODO xyz start async analysis


        return redisPrjVO;

    }

    private ProjectVO newProject(String userId,
                                 String prjName,
                                 String postfix,
                                 String prjRedisKey,
                                 byte[] content) {

        String accessCode = getAccessCode(userId);
        // create an archive
        ArchiveVO archiveVO = archiveService.nailArchive(prjName,
                ArchiveTypeEnum.checkAchvType(postfix), content);

        // create a new project
        Project tmProject = new Project();
        tmProject.setAccessCode(accessCode);
        tmProject.setName(prjName);
        tmProject.setUserId(userId);
        tmProject.setAchvId(archiveVO.getAchvId());
        projectMapper.insertSelective(tmProject);

        // cache this new project into redis
        ProjectVO projectVO = new ProjectVO();
        BeanUtils.copyProperties(projectVO, tmProject);
        redisUtil.opSetObjVal(prjRedisKey, projectVO);

        // build a relationship between user and project
        UserPrjRel userPrjRel = new UserPrjRel();
        userPrjRel.setPrjId(projectVO.getPrjId());
        userPrjRel.setUserId(userId);
        userPrjRelMapper.insertSelective(userPrjRel);

        return projectVO;
    }

}
