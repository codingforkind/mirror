package cn.com.cx.ps.mirror.service.analyzer.impl;

import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.configuration.MirrorProject;
import cn.com.cx.ps.mirror.project.variable.Class;
import cn.com.cx.ps.mirror.service.analyzer.ClassAnalyzerService;
import cn.com.cx.ps.mirror.tools.visitor.ClassDeclarationVisitor;

/**
 * Analyzing class infos for the prj
 */
@Service
public class ClassAnalyzerServiceImpl implements ClassAnalyzerService {

//    @Autowired
    private MirrorProject mirrorProject;

    private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Set<Class> extractClasses(CompilationUnit compilationUnit) {
//		TODO 待完善
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        compilationUnit.accept(classDeclarationVisitor);
        return classDeclarationVisitor.getCustomizedClasses();
	}

    
}
