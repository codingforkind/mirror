package cn.com.mirror.project.dao.entity;

import javax.persistence.*;

public class Archive {
    /**
     * archive id
     */
    @Id
    @Column(name = "achv_id")
    private String achvId;

    /**
     * archive zip type 
     */
    private String type;

    /**
     * archive zip file location
     */
    private String url;

    /**
     * unziped archive location
     */
    private String dir;

    /**
     * 获取archive id
     *
     * @return achv_id - archive id
     */
    public String getAchvId() {
        return achvId;
    }

    /**
     * 设置archive id
     *
     * @param achvId archive id
     */
    public void setAchvId(String achvId) {
        this.achvId = achvId;
    }

    /**
     * 获取archive zip type 
     *
     * @return type - archive zip type 
     */
    public String getType() {
        return type;
    }

    /**
     * 设置archive zip type 
     *
     * @param type archive zip type 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取archive zip file location
     *
     * @return url - archive zip file location
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置archive zip file location
     *
     * @param url archive zip file location
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取unziped archive location
     *
     * @return dir - unziped archive location
     */
    public String getDir() {
        return dir;
    }

    /**
     * 设置unziped archive location
     *
     * @param dir unziped archive location
     */
    public void setDir(String dir) {
        this.dir = dir;
    }
}