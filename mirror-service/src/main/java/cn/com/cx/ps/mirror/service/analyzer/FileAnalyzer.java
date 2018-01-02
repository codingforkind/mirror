package cn.com.cx.ps.mirror.service.analyzer;

import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Map;
import java.util.Set;

public interface FileAnalyzer {

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
}
