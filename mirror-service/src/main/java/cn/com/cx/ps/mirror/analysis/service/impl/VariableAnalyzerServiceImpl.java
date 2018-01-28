package cn.com.cx.ps.mirror.analysis.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.analysis.service.VariableAnalyzerService;
import cn.com.cx.ps.mirror.common.visitor.VariableVisitor;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;

@Service
public class VariableAnalyzerServiceImpl implements VariableAnalyzerService {
	// TODO 重新设计这块，变量类型判定应该与MirrorProject解耦

	@Autowired
	private VariableVisitor variableVisitor;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Set<Variable> extractVariables(CompilationUnit compilationUnit, Map<String, Set<Class>> prjClasses) {
		variableVisitor.setPrjClasses(prjClasses);
		compilationUnit.accept(variableVisitor);
		return variableVisitor.getVariables();
	}

	@Override
	public Map<String, Set<Variable>> mapProjectVariables(Map<String, CompilationUnit> prjCompUnits,
			Map<String, Set<Class>> prjClasses) {
		Assert.notNull(prjCompUnits, "Project compilation units parameter can not be NULL!");
		Map<String, Set<Variable>> map = new HashMap<>();

		Set<Entry<String, CompilationUnit>> entrySet = prjCompUnits.entrySet();
		Iterator<Entry<String, CompilationUnit>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, CompilationUnit> next = iterator.next();
			String javaFilePath = next.getKey();
			CompilationUnit compilationUnit = next.getValue();

			Set<Variable> variablesInJavaFile = extractVariables(compilationUnit, prjClasses);
			if (null == variablesInJavaFile) {
				log.info("No variables defined in java file, {}", javaFilePath);
			}
			map.put(javaFilePath, variablesInJavaFile);
		}
		return map;
	}

}
