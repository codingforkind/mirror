package cn.com.mirror.project.config;

import cn.com.mirror.annotation.Bind;
import lombok.Data;
import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Data
public class ProjectProperty {

    /**
     * The project's location
     */
    @Bind(value = "project.url")
    private String url;

    /**
     * Write the dependence into the graph db or not
     */
    @Bind(value = "project.graph.db.write")
    private Boolean enableWriteGraphDB;
}
