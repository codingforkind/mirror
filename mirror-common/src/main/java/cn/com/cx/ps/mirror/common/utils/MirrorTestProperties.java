/**
 * 
 */
package cn.com.cx.ps.mirror.common.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年3月6日
 */
@Data
@Component
@ConfigurationProperties(prefix = "prj")
@PropertySource("classpath:test.properties")
public class MirrorTestProperties {

	private String path;
	private String file;

}
