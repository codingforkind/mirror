package cn.com.cx.mirror.web.service.ps.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.com.cx.mirror.web.service.ps.ClassAnalyzerService;
import cn.com.cx.ps.mirror.common.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.mirror.java.variable.Class;

/**
 * Analyzing class infos for the prj
 */
@Service
public class ClassAnalyzerServiceImpl implements ClassAnalyzerService {

	@Override
	public Set<Class> extractClasses(String filePath, CompilationUnit compilationUnit) {
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(filePath);
		compilationUnit.accept(classDeclarationVisitor);
		return classDeclarationVisitor.getPrjClasses();
	}

	@Override
	public Map<String, Set<Class>> mapDefinedClasses(Map<String, CompilationUnit> prjCompilationUnits) {
		Assert.notNull(prjCompilationUnits, "Project compilation units are NULL!");
		Map<String, Set<Class>> map = null;

		Set<Entry<String, CompilationUnit>> entrySet = prjCompilationUnits.entrySet();
		Iterator<Entry<String, CompilationUnit>> iterator = entrySet.iterator();
		if(iterator.hasNext()) {
			map = new HashMap<>();
		}
		
		while (iterator.hasNext()) {
			Entry<String, CompilationUnit> next = iterator.next();
			String filePath = next.getKey();
			CompilationUnit unit = next.getValue();
			map.put(filePath, extractClasses(filePath, unit));
		}
		return map;
	}

}
