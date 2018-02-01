package cn.com.cx.ps.mirror.analysis.service;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;

public interface VariableAnalyzerService {

	/**
	 * Extract variables defined in one java compilation unit
	 */
	public Set<Variable> extractVariables(String javaFilePath, CompilationUnit compilationUnit,
			Map<String, Set<Class>> prjClasses);

	/**
	 * Mapping all of the variable defined in the project
	 */
	public Map<String, Set<Variable>> mapProjectVariables(Map<String, CompilationUnit> prjCompUnits,
			Map<String, Set<Class>> prjClasses);
}
