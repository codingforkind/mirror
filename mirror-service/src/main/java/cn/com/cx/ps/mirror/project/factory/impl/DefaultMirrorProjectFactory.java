package cn.com.cx.ps.mirror.project.factory.impl;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.analysis.service.ClassAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.FileAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.PackageAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.VariableAnalyzerService;
import cn.com.cx.ps.mirror.configuration.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.project.factory.MirrorProjectFactory;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年1月23日
 */
@Component
@ConditionalOnBean(value = { FileAnalyzerService.class, PackageAnalyzerService.class, ClassAnalyzerService.class,
		VariableAnalyzerService.class })
@EnableConfigurationProperties(MirrorProjectProperties.class)
public class DefaultMirrorProjectFactory implements MirrorProjectFactory {

	private MirrorProjectProperties properties;

	@Autowired
	private FileAnalyzerService fileAnalyzerService;
	@Autowired
	private PackageAnalyzerService packageAnalyzerService;
	@Autowired
	private ClassAnalyzerService classAnalyzerService;
	@Autowired
	private VariableAnalyzerService variableAnalyzerService;

	public DefaultMirrorProjectFactory(MirrorProjectProperties projectProperties) {
		this.properties = projectProperties;
	}

	@Override
	public MirrorProject initMirrorProject(MirrorProject mirrorProject) {
		Assert.notNull(mirrorProject, "MirrorProject is null, please generate one!");

		// compilation units
		Map<String, CompilationUnit> mapDefinedCompilationUnits = fileAnalyzerService
				.extractCompilationUnits(mirrorProject.getPrjJavaFiles());
		Assert.notNull(mapDefinedCompilationUnits, "No compilation units found in the project, existing!");
		mirrorProject.setPrjCompilationUnits(mapDefinedCompilationUnits);

		// packages
		Map<String, String> mapDefinedPackages = packageAnalyzerService
				.extractPackages(mirrorProject.getPrjCompilationUnits());
		Assert.notNull(mapDefinedPackages, "No packages were defined in the project!");
		mirrorProject.setPrjPackages(mapDefinedPackages);

		// classes
		Map<String, Set<Class>> mapDefinedClasses = classAnalyzerService
				.mapDefinedClasses(mirrorProject.getPrjCompilationUnits());
		Assert.notNull(mapDefinedClasses, "No classes were defined in the project!");
		mirrorProject.setPrjClasses(mapDefinedClasses);

		Map<String, Set<Variable>> mapDefinedVariables = variableAnalyzerService
				.mapProjectVariables(mirrorProject.getPrjCompilationUnits(), mirrorProject.getPrjClasses());
		Assert.notNull(mapDefinedVariables, "No variables were defined in the project!");
		mirrorProject.setPrjVariables(mapDefinedVariables);
		
		return mirrorProject;
	}

	@Override
	@Bean
	public MirrorProject mirrorProject() {
		return initMirrorProject(new MirrorProject(properties));
	}

}
