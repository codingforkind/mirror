package cn.com.mirror.nas.service;

import java.io.InputStream;

public interface NasService {
    /**
     * Upload files to nas
     * @param content
     */
    String uploadArchive(byte[] content);
}
