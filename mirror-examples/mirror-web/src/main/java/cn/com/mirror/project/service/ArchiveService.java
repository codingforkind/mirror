package cn.com.mirror.project.service;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.project.dao.entity.Archive;
import cn.com.mirror.project.pojo.ArchiveVO;

public interface ArchiveService {
    /**
     * Create an archive and store the zipped src code.
     *
     * @param prjName     project name
     * @param archiveType zipped src file's type
     * @param content     zipped src file's content
     */
    ArchiveVO nailArchive(String prjName, ArchiveTypeEnum archiveType, byte[] content);
}
