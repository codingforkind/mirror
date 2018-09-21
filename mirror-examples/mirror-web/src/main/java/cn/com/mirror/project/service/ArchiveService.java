package cn.com.mirror.project.service;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.project.dao.entity.Archive;
import cn.com.mirror.project.pojo.ArchiveVO;

public interface ArchiveService {
    /**
     * Create an archive and store the zipped code code.
     *
     * @param prjName     project name
     * @param archiveType zipped code file's type
     * @param content     zipped code file's content
     */
    ArchiveVO nailArchive(String prjName, ArchiveTypeEnum archiveType, byte[] content);
}
