package cn.com.mirror.project.dao.entity;

import javax.persistence.*;

public class Graph {
    /**
     * graph id
     */
    @Id
    @Column(name = "graph_id")
    private String graphId;

    /**
     * graph database linking url
     */
    private String url;

    private String username;

    private String password;

    private String host;

    private Short port;

    /**
     * 获取graph id
     *
     * @return graph_id - graph id
     */
    public String getGraphId() {
        return graphId;
    }

    /**
     * 设置graph id
     *
     * @param graphId graph id
     */
    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    /**
     * 获取graph database linking url
     *
     * @return url - graph database linking url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置graph database linking url
     *
     * @param url graph database linking url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return port
     */
    public Short getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(Short port) {
        this.port = port;
    }
}