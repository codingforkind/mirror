package cn.com.cx.ps.mirror.project;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.configuration.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.java.ClassFile;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import lombok.Data;

@Data
public class MirrorProject {
	private Set<String> prjJavaFiles = new HashSet<>(); // all java files in the project
	private int fileCount; // the number of all java files
	private MirrorProjectProperties properties;

	
	// all the following four properties's key are java file path
	private Map<String, CompilationUnit> prjCompilationUnits; // all compilation unit in the project
	private Map<String, String> prjPackages; // all packages defined in the project
	private Map<String, Set<Class>> prjClasses; // all classes defined in the project
	private Map<String, Set<Variable>> prjVariables; // all variables defined in the project
	
	
	private Map<String, ClassFile> prjClassesFile = new HashMap<>();

	private Logger log = LoggerFactory.getLogger(getClass());
	
	public MirrorProject(MirrorProjectProperties mirrorProjectProperties) {
		log.info("Create a mirror project!");
		this.properties = mirrorProjectProperties;
		this.initjavaFiles(new File(this.properties.getPath()));
	}

	/**
	 * <p>extract all the java file in the project and store it in the property of @see
	 * prjJavaFiles.</p>
	 * <p>get all files in this project ant the count of the files(".java")</p>
	 * @param dir
	 */
	private void initjavaFiles(File dir) {
		File[] fs = dir.listFiles();
		String tmpPath = null;
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].getAbsolutePath().endsWith(".java")) {
				tmpPath = fs[i].getAbsolutePath();
				this.fileCount += 1;
				prjJavaFiles.add(tmpPath);
			}
			if (fs[i].isDirectory()) {
				initjavaFiles(fs[i]);
			} // end if
		} // end for
	}
	
}
