package cn.com.cx.ps.mirror.project.factory.impl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import cn.com.cx.ps.mirror.configuration.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.project.factory.MirrorProjectFactory;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年1月23日
 */
@Component
@EnableConfigurationProperties(MirrorProjectProperties.class)
public class DefaultMirrorProjectFactory implements MirrorProjectFactory {

	private MirrorProjectProperties properties;

	public DefaultMirrorProjectFactory(MirrorProjectProperties projectProperties) {
		this.properties = projectProperties;
	}

	@Override
	public MirrorProject generateMirrorProject() {
		return new MirrorProject(properties);
	}

}
