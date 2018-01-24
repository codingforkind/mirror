package cn.com.cx.ps.mirror.analysis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;


/**
 * Analyzing file infos for the prj
 */
public interface FileAnalyzerService {

    /**
     * Extract all the CompilationUnits in the project.
     *
     * @param javaFiles
     * @return
     */
    public Map<String, CompilationUnit> extractCompilationUnits(Set<String> javaFiles);

    /**
     * Parsing the java file to CompilationUnit
     *
     * @param javaFile
     * @return
     */
    public CompilationUnit parserCompilationUnit(String javaFile);
    

    /**
     * 提取java文件中所有代码行
     * @param filePath
     * @return
     */
    public List<String> extractLineNums(String filePath);
    

}
