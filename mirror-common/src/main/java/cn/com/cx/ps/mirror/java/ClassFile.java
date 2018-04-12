package cn.com.cx.ps.mirror.java;

import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Piggy
 *
 * @description
 * @since 2018年3月12日
 */
@Data
public class ClassFile {

	private String file;
	private String pkg;
	private List<String> statements;
	private CompilationUnit compilationUnit;
	private Set<Class> classesInFile;
	private Set<Variable> variablesInFile;

	public ClassFile(String file) {
		this.file = file;
	}

}
