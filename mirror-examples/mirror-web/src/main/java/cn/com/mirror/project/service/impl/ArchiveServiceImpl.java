package cn.com.mirror.project.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.nas.service.NasService;
import cn.com.mirror.project.dao.entity.Archive;
import cn.com.mirror.project.dao.mapper.ArchiveMapper;
import cn.com.mirror.project.pojo.ArchiveVO;
import cn.com.mirror.project.service.ArchiveService;
import cn.com.mirror.project.service.UnzipService;
import cn.com.mirror.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private NasService nasService;
    @Autowired
    private UnzipService unzipService;

    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    @Transactional
    public ArchiveVO nailArchive(String prjName, ArchiveTypeEnum archiveType, byte[] content) {
        // TODO xyz async service: store zip project file, unzip it and analyze it.
        String filePath = nasService.uploadArchive(prjName, archiveType.getPostfix(), content);

        Archive archive = new Archive();

        String dir = unzipService.extractZippedSrcFile(prjName, filePath, archiveType);
        // analyze it [next move]

        archive.setType(archiveType.getPostfix());
        archive.setDir(dir);
        archive.setUrl(dir);
        archiveMapper.insertSelective(archive);

        ArchiveVO archiveVO = new ArchiveVO();
        BeanUtils.copyProperties(archiveVO, archive);
        return archiveVO;
    }

}
