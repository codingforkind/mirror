package cn.com.mirror.project.config;

import cn.com.mirror.annotation.Bind;
import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Getter
public class ProjectProperty {

    @Bind(value = "project.url")
    private String url;
}
