package cn.com.cx.ps.mirror.java;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;

import java.util.List;
import java.util.Set;

public class ClassFile {

	private String fileName;
	private List<String> statements;
	private CompilationUnit compilationUnit;
	private Set<Class> classesInFile;
	private Set<Variable> variablesInFile;

	public ClassFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getStatements() {
		return statements;
	}

	public void setStatements(List<String> statements) {
		this.statements = statements;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public void setCompilationUnit(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public Set<Class> getClassesInFile() {
		return classesInFile;
	}

	public void setClassesInFile(Set<Class> classesInFile) {
		this.classesInFile = classesInFile;
	}

	public Set<Variable> getVariablesInFile() {
		return variablesInFile;
	}

	public void setVariablesInFile(Set<Variable> variablesInFile) {
		this.variablesInFile = variablesInFile;
	}

}
