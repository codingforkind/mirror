package cn.com.mirror.nas.service;

public interface NasService {
    /**
     * Upload files to nas
     * @param prjName
     * @param postfix
     * @param content
     */
    String uploadArchive(String prjName, String postfix, byte[] content);
}
