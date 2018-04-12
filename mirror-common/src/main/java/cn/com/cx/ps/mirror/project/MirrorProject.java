package cn.com.cx.ps.mirror.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.common.exceptions.ProjectException;
import cn.com.cx.ps.mirror.config.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.java.ClassFile;
import lombok.Data;

@Data
public class MirrorProject {
	private Set<String> prjJavaFiles = new HashSet<>(); // all java files in the project
	private int fileCount; // the number of all java files
	private MirrorProjectProperties properties;

	// all the following four properties's key are java file path
	private Map<String, String> prjPackages; // all packages defined in the project
	
	private List<ClassFile> classList;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public MirrorProject(MirrorProjectProperties mirrorProjectProperties) {
		log.info("Create a mirror project!");
		Assert.notNull(mirrorProjectProperties, "Mirror project properties are NULL");
		log.info("Project name: [{}], path: [{}]", mirrorProjectProperties.getName(),
				mirrorProjectProperties.getPath());
		this.properties = mirrorProjectProperties;

		File file = new File(this.properties.getPath());
		log.info("Path is exists: [{}]",  file.exists());
		if (!file.exists()) {
			throw new ProjectException("Mirror project path is not exists!");
		}
		
		this.initjavaFiles(file);
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
	
	public void addClassFile(ClassFile classFile) {
		if (null == this.classList || this.classList.isEmpty()) {
			this.classList = new ArrayList<>();
			this.classList.add(classFile);
		}
		this.classList.add(classFile);
	}

}
