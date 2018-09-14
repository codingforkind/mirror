package cn.com.mirror.project.dao.entity;

import javax.persistence.*;

@Table(name = "user_prj_rel")
public class UserPrjRel {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "prj_id")
    private String prjId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return prj_id
     */
    public String getPrjId() {
        return prjId;
    }

    /**
     * @param prjId
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }
}