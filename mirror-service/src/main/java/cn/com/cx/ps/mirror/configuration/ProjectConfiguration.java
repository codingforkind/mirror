package cn.com.cx.ps.mirror.configuration;

import cn.com.cx.ps.mirror.configuration.properties.ProjectProperties;
import cn.com.cx.ps.mirror.exceptions.ProjectException;
import cn.com.cx.ps.mirror.project.MirrorProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@EnableConfigurationProperties(value = ProjectProperties.class)
public class ProjectConfiguration {
    @Autowired
    private ProjectProperties projectProperties;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public MirrorProject mirrorProject() {
        MirrorProject mirrorProject = null;
        try {
            mirrorProject = new MirrorProject(new File(this.projectProperties.getPath()), this.projectProperties.getName());
        } catch (ProjectException e) {
            log.error("Loading project failed.", e);
            e.printStackTrace();
        }

        return mirrorProject;
    }

}
