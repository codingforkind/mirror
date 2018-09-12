package cn.com.mirror.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "mirror")
@Configuration
public class MirrorProperties {

    private int maxCliNum;

}
