package cn.com.cx.ps.mirror.analysis.service.impl;

import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.analysis.service.ClassAnalyzerService;
import cn.com.cx.ps.mirror.common.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.mirror.java.variable.Class;

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
