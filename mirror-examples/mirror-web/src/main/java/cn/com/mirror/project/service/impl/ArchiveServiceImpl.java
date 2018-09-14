package cn.com.mirror.project.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.nas.service.NasService;
import cn.com.mirror.project.dao.entity.Archive;
import cn.com.mirror.project.service.ArchiveService;
import cn.com.mirror.project.service.UnzipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private NasService nasService;

    @Autowired
    private UnzipService unzipService;

    @Override
    public Archive nailArchive(String prjName, ArchiveTypeEnum archiveType, byte[] content) {
        // TODO xyz async service: store zip project file, unzip it and analyze it.
        String filePath = nasService.uploadArchive(prjName, archiveType.getPostfix(), content);


        Archive archive = new Archive();

        String dir = unzipService.extractZippedSrcFile(filePath, archiveType);
        // analyze it [next move]

        archive.setType(archiveType.getPostfix());
        archive.setDir(dir);
        archive.setUrl(dir);

        return archive;
    }

}
