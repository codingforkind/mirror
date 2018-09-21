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
     * access src for this project
     */
    private String accessCode;

    /**
     * user info related
     */
    private String userId;

    /**
     * project's source src
     */
    private String achvId;

    /**
     * graph info related
     */
    private String graphId;
}
