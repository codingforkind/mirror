package cn.com.cx.ps.mirror.service.analyzer;

import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Map;

public interface PackageAnalyzerService {

    /**
     * Extract the packages in the project.
     * @param prjCompUnits 
     * <p> String: Java file
     * <p> CompilationUnit: The java file's compilation unit 
     */
    public Map<String, String> extractPackages(Map<String, CompilationUnit> prjCompUnits);


    /**
     * Parser the package info for a compilation unit.
     * @param compilationUnit
     */
    public String parserPackage(CompilationUnit compilationUnit);
}
