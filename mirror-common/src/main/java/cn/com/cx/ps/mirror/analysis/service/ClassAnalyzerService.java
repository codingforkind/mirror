package cn.com.cx.ps.mirror.analysis.service;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.java.variable.Class;

public interface ClassAnalyzerService {
	/**
	 * 从编译单元中提取类
	 * @param compilationUnit 编译单元
	 * @return
	 */
	public Set<Class> extractClasses(String filePath, CompilationUnit compilationUnit);
	
	/**
	 * 把工程中定义的所有类进行映射
	 * @param prjCompilationUnits 工程中定义的所有类
	 * @return
	 */
	public Map<String, Set<Class>> mapDefinedClasses(Map<String, CompilationUnit> prjCompilationUnits);
}
