package cn.com.cx.ps.mirror.project.service.impl;

import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.analysis.service.ClassAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.FileAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.PackageAnalyzerService;
import cn.com.cx.ps.mirror.analysis.service.VariableAnalyzerService;
import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.project.service.ProjectInitializationService;

@Component
public class ProjectInitializationServiceImpl implements ProjectInitializationService {
	
	@Autowired
	private FileAnalyzerService fileAnalyzerService;
	@Autowired
	private ClassAnalyzerService classAnalyzerService;
	@Autowired
	private PackageAnalyzerService packageAnalyzerService;
	@Autowired
	private VariableAnalyzerService variableAnalyzerService;

	@Override
	public boolean initMirrorProject(MirrorProject mirrorProject) {
		
		Assert.notNull(mirrorProject, "MirrorProject is null, please generate one!");
		
		Map<String, CompilationUnit> prjCompUnits = fileAnalyzerService
				.extractCompilationUnits(mirrorProject.getPrjJavaFiles());
		Assert.notNull(prjCompUnits, "No compilation units extract for this project, existing!");
		mirrorProject.setPrjCompilationUnits(prjCompUnits);
		
		
		
		return false;
	}

}
