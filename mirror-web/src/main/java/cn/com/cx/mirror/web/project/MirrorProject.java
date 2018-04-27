package cn.com.cx.mirror.web.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.com.cx.mirror.web.config.property.MirrorProjectProperties;
import cn.com.cx.ps.mirror.java.ClassFile;
import cn.com.cx.ps.mirror.utils.FileUtils;
import lombok.Data;

@Data
public class MirrorProject {
	private MirrorProjectProperties properties;

	private Set<String> prjJavaFiles = new HashSet<>(); // all java files in the project
	private int fileCount; // the number of all java files
	private Map<String, String> prjPackages; // all packages defined in the project

	private List<ClassFile> classList;

	private Logger log = LoggerFactory.getLogger(getClass());

	public MirrorProject(MirrorProjectProperties mirrorProjectProperties) {
		Assert.notNull(mirrorProjectProperties, "Mirror project properties are NULL");
		log.info("Create a mirror project, name: [{}], path: [{}]", mirrorProjectProperties.getName(),
				mirrorProjectProperties.getPath());
		this.properties = mirrorProjectProperties;
		this.prjJavaFiles = FileUtils.prjJavaFileSet(properties.getPath());
		this.fileCount = this.prjJavaFiles.size();
	}

	public void addClassFile(ClassFile classFile) {
		if (null == this.classList || this.classList.isEmpty()) {
			this.classList = new ArrayList<>();
			this.classList.add(classFile);
		}
		this.classList.add(classFile);
	}
}
