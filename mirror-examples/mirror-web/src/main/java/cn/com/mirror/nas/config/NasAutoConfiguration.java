package cn.com.mirror.nas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = NasProperties.class)
public class NasAutoConfiguration {

    @Autowired
    private NasProperties nasProperties;

}
