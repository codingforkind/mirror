package cn.com.cx.ps.mirror.project;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.configuration.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.java.ClassFile;
import cn.com.cx.ps.mirror.java.variable.Class;

public class MirrorProject {
	private Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * all java files in the project
	 */
	private Set<String> prjJavaFiles = new HashSet<>();
	/**
	 * the number of all java files
	 */
	private int fileCount;
	private MirrorProjectProperties properties;

	private Map<String, ClassFile> prjClassesFile = new HashMap<>();

	public MirrorProject(MirrorProjectProperties mirrorProjectProperties) {
		log.info("Create a mirror project!");
		this.properties = mirrorProjectProperties;
		this.initjavaFiles(new File(this.properties.getPath()));
	}

	public int getFileCount() {
		return fileCount;
	}

	public Map<String, ClassFile> getPrjClassesFile() {
		return prjClassesFile;
	}

	public boolean classInProject(String qualifiedClassName) {
		for (ClassFile classFile : this.prjClassesFile.values()) {
			for (Class customizedClass : classFile.getClassesInFile()) {
				if (customizedClass.getQualifiedName().contains(qualifiedClassName))
					return true;
				continue;
			}
		}
		return false;
	}

	public Set<String> getPrjJavaFiles() {
		return prjJavaFiles;
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
	
	@PostConstruct
	private void initProject() {
		
	}
	
}
