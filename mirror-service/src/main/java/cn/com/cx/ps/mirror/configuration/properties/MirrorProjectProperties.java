package cn.com.cx.ps.mirror.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;
import java.io.File;

@ConfigurationProperties(prefix = "mirror.project")
public class MirrorProjectProperties {
    /**
     * project name
     */
    @NotNull
    private String name;
    /**
     * project location
     */
    @NotNull
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}