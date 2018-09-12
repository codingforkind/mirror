package cn.com.mirror.nas.service;

import java.io.InputStream;

public interface NasService {
    /**
     * Upload files to nas
     * @param content
     */
    void uploadArchive(byte[] content);
}
