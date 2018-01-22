package cn.com.cx.ps.mirror.service.analyzer.impl;

import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.project.variable.Class;
import cn.com.cx.ps.mirror.service.analyzer.ClassAnalyzerService;
import cn.com.cx.ps.mirror.visitor.ClassDeclarationVisitor;

/**
 * Analyzing class infos for the prj
 */
@Service
public class ClassAnalyzerServiceImpl implements ClassAnalyzerService {

	@Override
	public Set<Class> extractClasses(CompilationUnit compilationUnit) {
//		TODO 待完善
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        compilationUnit.accept(classDeclarationVisitor);
        return classDeclarationVisitor.getCustomizedClasses();
	}

    
}
