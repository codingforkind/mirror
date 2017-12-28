package cn.com.cx.ps.mirror.project;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("project.properties")
@ConfigurationProperties(prefix = "cn/com/cx/ps/mirror/project")
@Component
public class ProjectProperty {
    private String dir;
    private String file;
    private int prime;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }
}
