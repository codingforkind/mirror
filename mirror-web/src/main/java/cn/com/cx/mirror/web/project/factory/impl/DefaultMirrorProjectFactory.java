package cn.com.cx.mirror.web.project.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.com.cx.mirror.web.config.property.MirrorProjectProperties;
import cn.com.cx.mirror.web.project.MirrorProject;
import cn.com.cx.mirror.web.project.factory.MirrorProjectFactory;
import cn.com.cx.mirror.web.service.ps.ClassAnalyzerService;
import cn.com.cx.mirror.web.service.ps.FileAnalyzerService;
import cn.com.cx.mirror.web.service.ps.PackageAnalyzerService;
import cn.com.cx.mirror.web.service.ps.VariableAnalyzerService;
import cn.com.cx.ps.mirror.utils.FileUtils;
import cn.com.cx.ps.mirror.java.ClassFile;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;

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
		if(null == mirrorProject) {
			mirrorProject = new MirrorProject(this.properties);
		}
		
		Map<String, Set<Class>> prjClasses = new HashMap<>();
		for(String file : mirrorProject.getPrjJavaFiles()) {
			CompilationUnit compilationUnit = fileAnalyzerService.parserCompilationUnit(file);
			String pkg = packageAnalyzerService.parserPackage(compilationUnit);
			Set<Class> classSet = classAnalyzerService.extractClasses(file, compilationUnit);
			prjClasses.put(file, classSet);
			
			ClassFile classFile = new ClassFile(file);
			classFile.setCompilationUnit(compilationUnit);
			classFile.setFile(file);
			classFile.setPkg(pkg);
			classFile.setClassesInFile(classSet);
			classFile.setStatements(FileUtils.listCodeLines(file));
			mirrorProject.addClassFile(classFile);
		}

		for (ClassFile classFile : mirrorProject.getClassList()) {
			Set<Variable> variableSet = variableAnalyzerService.extractVariables(classFile.getFile(),
					classFile.getCompilationUnit(), prjClasses);
			classFile.setVariablesInFile(variableSet);
		}
		// TODO 已经找到该文件中所有变量，此时需要把所有变量放到特定的行中，并且不同行的变量是统一的
		return mirrorProject;
	}

	@Override
	@Bean
	public MirrorProject mirrorProject() {
		return initMirrorProject(new MirrorProject(properties));
	}

}
