package cn.com.cx.ps.mirror.analysis.service.impl;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.analysis.service.VariableAnalyzerService;
import cn.com.cx.ps.mirror.common.visitor.VariableVisitor;
import cn.com.cx.ps.mirror.java.variable.Variable;

@Service
public class VariableAnalyzerServiceImpl implements VariableAnalyzerService {
	// TODO 重新设计这块，变量类型判定应该与MirrorProject解耦

	@Autowired
	private VariableVisitor variableVisitor;

	@Override
	public Set<Variable> extractVariables(CompilationUnit compilationUnit) {
		return null;
	}

	@Override
	public Map<String, Set<Variable>> mapProjectVariables(Set<CompilationUnit> prjCompilationUnits) {
		// TODO Auto-generated method stub
		return null;
	}

}
