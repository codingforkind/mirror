package cn.com.mirror.project.src;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.utils.PropertyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
public class LocalLoader implements CodeLoader {
    private static ProjectProperty projectProperty = null;

    public static final ProjectProperty getPrjProperty() {
        if (null == projectProperty) {
            synchronized (LocalLoader.class) {
                if (null == projectProperty) {
                    Properties props = new Properties();
                    InputStream stream = LocalLoader.class.getResourceAsStream("/project.properties");
                    try {
                        props.load(stream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    projectProperty = new ProjectProperty();
                    try {
                        PropertyUtils.mappingProperties(props, projectProperty);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    return projectProperty;
                }
            }
        }

        return projectProperty;
    }

    @Override
    public String getRepositoryUrl() {
        return getPrjProperty().getUrl();
    }
}
