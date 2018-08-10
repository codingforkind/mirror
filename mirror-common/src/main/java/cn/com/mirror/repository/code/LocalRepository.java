package cn.com.mirror.repository.code;

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
public class LocalRepository implements CodeRepository {

    @Override
    public String getRepositoryUrl() {
        Properties props = new Properties();
        InputStream stream = LocalRepository.class.getResourceAsStream("project.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProjectProperty projectProperty = new ProjectProperty();
        try {
            PropertyUtils.mappingProperties(props, projectProperty);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return projectProperty.getUrl();
    }
}
