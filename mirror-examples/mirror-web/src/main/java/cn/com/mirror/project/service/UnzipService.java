package cn.com.mirror.project.service;

import cn.com.mirror.constant.ArchiveTypeEnum;

public interface UnzipService {

    /**
     * Extract zipped src file and write it into the hard-drive.
     * @param filePath
     * @param archiveType
     * @return
     */
    String extractZippedSrcFile(String filePath, ArchiveTypeEnum archiveType);
}