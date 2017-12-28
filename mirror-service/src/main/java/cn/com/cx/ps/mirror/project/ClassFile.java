package cn.com.cx.ps.mirror.project;

import cn.com.cx.ps.mirror.variable.CustomizedClass;
import cn.com.cx.ps.mirror.variable.Variable;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.List;
import java.util.Set;

public class ClassFile {

	private String fileName;
	private List<String> statements;
	private CompilationUnit compilationUnit;
	private Set<CustomizedClass> classesInFile;
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

	public Set<CustomizedClass> getClassesInFile() {
		return classesInFile;
	}

	public void setClassesInFile(Set<CustomizedClass> classesInFile) {
		this.classesInFile = classesInFile;
	}

	public Set<Variable> getVariablesInFile() {
		return variablesInFile;
	}

	public void setVariablesInFile(Set<Variable> variablesInFile) {
		this.variablesInFile = variablesInFile;
	}

}
