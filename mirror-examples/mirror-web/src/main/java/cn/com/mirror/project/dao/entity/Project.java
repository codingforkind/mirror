package cn.com.mirror.project.dao.entity;

import javax.persistence.*;

public class Project {
    /**
     * project id
     */
    @Id
    @Column(name = "prj_id")
    private String prjId;

    /**
     * project name
     */
    private String name;

    /**
     * access src for this project
     */
    @Column(name = "access_code")
    private String accessCode;

    /**
     * user info related
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * project's source src
     */
    @Column(name = "achv_id")
    private String achvId;

    /**
     * graph info related
     */
    @Column(name = "graph_id")
    private String graphId;

    /**
     * 获取project id
     *
     * @return prj_id - project id
     */
    public String getPrjId() {
        return prjId;
    }

    /**
     * 设置project id
     *
     * @param prjId project id
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    /**
     * 获取project name
     *
     * @return name - project name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置project name
     *
     * @param name project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取access src for this project
     *
     * @return access_code - access src for this project
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * 设置access src for this project
     *
     * @param accessCode access src for this project
     */
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * 获取user info related
     *
     * @return user_id - user info related
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置user info related
     *
     * @param userId user info related
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取project's source src
     *
     * @return achv_id - project's source src
     */
    public String getAchvId() {
        return achvId;
    }

    /**
     * 设置project's source src
     *
     * @param achvId project's source src
     */
    public void setAchvId(String achvId) {
        this.achvId = achvId;
    }

    /**
     * 获取graph info related
     *
     * @return graph_id - graph info related
     */
    public String getGraphId() {
        return graphId;
    }

    /**
     * 设置graph info related
     *
     * @param graphId graph info related
     */
    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }
}