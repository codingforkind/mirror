package cn.com.cx.ps.mirror.configuration;

import cn.com.cx.ps.mirror.configuration.properties.MirrorProjectProperties;
import cn.com.cx.ps.mirror.exceptions.ProjectException;
import cn.com.cx.ps.mirror.project.ClassFile;
import cn.com.cx.ps.mirror.project.variable.Class;
import cn.com.cx.ps.mirror.tools.runner.ClassAnalyzerRunner;
import cn.com.cx.ps.mirror.tools.runner.FileAnalyzerRunner;
import cn.com.cx.ps.mirror.tools.runner.PackageAnalyzerRunner;
import cn.com.cx.ps.mirror.tools.visitor.VariableVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//@ConditionalOnClass(MirrorProjectProperties.class)
@EnableConfigurationProperties(MirrorProjectProperties.class)
public class MirrorProject {
	private static Logger logger = LoggerFactory.getLogger(MirrorProject.class);
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

	private ExecutorService executorService = Executors.newFixedThreadPool(13);

	public MirrorProject(MirrorProjectProperties mirrorProjectProperties) throws ProjectException {
		this.properties = mirrorProjectProperties;
		this.initjavaFiles(new File(this.properties.getPath()));
	}

	// TODO 重构，至此，继续Variable解析和存储变量
	@PostConstruct
	public void startFileAnalyzer() {
		// initialized, start to analyze
		FileAnalyzerRunner fileAnalyzer = new FileAnalyzerRunner(this.prjJavaFiles);
		Future<?> fileSubmit = executorService.submit(fileAnalyzer);
		while (fileSubmit.isDone()) {
			PackageAnalyzerRunner packageAnalyzer = new PackageAnalyzerRunner(fileAnalyzer.getPrjCompUnits());
			Future<?> pkgSubmit = executorService.submit(packageAnalyzer);

			ClassAnalyzerRunner classAnalyzer = new ClassAnalyzerRunner(fileAnalyzer.getPrjCompUnits());
			Future<?> clsSubmit = executorService.submit(classAnalyzer);

			// waiting for packages and classes' analysis is done
			while (pkgSubmit.isDone() && clsSubmit.isDone()) {
				// TODO 保存包和类的信息

				VariableVisitor variableVisitor = null;
				for (String tmpPath : prjJavaFiles) {
					variableVisitor = new VariableVisitor(tmpPath, this);

					ClassFile classFile = new ClassFile(tmpPath);
					classFile.getCompilationUnit().accept(variableVisitor);
					classFile.setVariablesInFile(variableVisitor.getVariables());

					this.prjClassesFile.put(tmpPath, classFile);
				}
			}
		}

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
	 * <p>
	 * extract all the java file in the project and store it in the property of @see
	 * prjJavaFiles.
	 * </p>
	 * <p>
	 * get all files in this project ant the count of the files(".java")
	 * </p>
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
