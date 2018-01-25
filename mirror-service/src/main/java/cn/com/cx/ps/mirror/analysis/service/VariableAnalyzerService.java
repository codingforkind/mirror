package cn.com.cx.ps.mirror.analysis.service;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.java.variable.Variable;

public interface VariableAnalyzerService {

	/**
	 * Extract variables defined in one java compilation unit
	 * @param compilationUnit
	 * @return
	 */
	public Set<Variable> extractVariables(CompilationUnit compilationUnit);

	/**
	 * Mapping all of the variable defined in the project
	 * @param prjCompilationUnits
	 * @return
	 */
	public Map<String, Set<Variable>> mapProjectVariables(Set<CompilationUnit> prjCompilationUnits);
}
