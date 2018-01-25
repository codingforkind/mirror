package cn.com.cx.ps.mirror.project.service.impl;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.analysis.service.ClassAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.FileAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.PackageAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.VariableAnalyzerService;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.project.service.ProjectInitializationService;

@Component
public class ProjectInitializationServiceImpl implements ProjectInitializationService {

	@Autowired
	private FileAnalyzerService fileAnalyzerService;
	@Autowired
	private PackageAnalyzerService packageAnalyzerService;
	@Autowired
	private ClassAnalyzerService classAnalyzerService;
	@Autowired
	private VariableAnalyzerService variableAnalyzerService;

	@Override
	public boolean initMirrorProject(MirrorProject mirrorProject) {
		Assert.notNull(mirrorProject, "MirrorProject is null, please generate one!");

		// compilation units
		Map<String, CompilationUnit> prjCompUnits = fileAnalyzerService
				.extractCompilationUnits(mirrorProject.getPrjJavaFiles());
		Assert.notNull(prjCompUnits, "No compilation units found in the project, existing!");
		mirrorProject.setPrjCompilationUnits(prjCompUnits);
		
		// packages
		Map<String, String> prjPackages = packageAnalyzerService.extractPackages(mirrorProject.getPrjCompilationUnits());
		Assert.notNull(prjCompUnits, "No packages were defined in the project!");
		mirrorProject.setPrjPackages(prjPackages);

		// classes
		Map<String, Set<Class>> mapDefinedClasses = classAnalyzerService
				.mapDefinedClasses(mirrorProject.getPrjCompilationUnits());
		Assert.notNull(mapDefinedClasses, "No classes were defined in the project!");
		
		
		
		return false;
	}

}
