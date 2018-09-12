package cn.com.mirror.nas.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "nas")
public class NasProperties {

    @NotNull
    private String location;

}
