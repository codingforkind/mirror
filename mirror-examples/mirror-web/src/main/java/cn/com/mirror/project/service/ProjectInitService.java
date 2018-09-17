package cn.com.mirror.project.service;


import cn.com.mirror.project.pojo.ProjectVO;

public interface ProjectInitService {

    /**
     * Generate a project for the uploaded zipped project.
     * @param accessCode
     * @param userId
     * @param originalFileName zipped project file name
     * @param content the content of the zipped project file
     */
    ProjectVO genProject(String accessCode, String userId, String originalFileName, byte[] content);

    /**
     * Retrieve or generate the access code for one project.
     */
    String getAccessCode(String userId);
}
