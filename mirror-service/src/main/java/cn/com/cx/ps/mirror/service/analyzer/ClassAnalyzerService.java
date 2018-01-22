package cn.com.cx.ps.mirror.service.analyzer;

import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.project.variable.Class;

public interface ClassAnalyzerService {
	/**
	 * 从编译单元中提取类
	 * @param compilationUnit 编译单元
	 * @return
	 */
	public Set<Class> extractClasses(CompilationUnit compilationUnit);
}
