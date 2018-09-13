package cn.com.mirror.project.pojo;

import lombok.Data;

@Data
public class ProjectVO {

    /**
     * project id
     */
    private String prjId;

    /**
     * project name
     */
    private String name;

    /**
     * access code for this project
     */
    private String accessCode;

    /**
     * user info related
     */
    private String userId;

    /**
     * project's source code
     */
    private String achvId;

    /**
     * graph info related
     */
    private String graphId;
}
