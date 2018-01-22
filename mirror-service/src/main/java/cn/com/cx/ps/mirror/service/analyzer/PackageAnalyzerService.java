package cn.com.cx.ps.mirror.service.analyzer;

import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Map;

public interface PackageAnalyzerService {

    /**
     * Extract the packages in the project.
     *
     * @param prjCompUnits
     * @return
     */
    public Map<String, String> extractPackages(Map<String, CompilationUnit> prjCompUnits);


    /**
     * parser the package info for a compilation unit.
     * @param compilationUnit
     * @return
     */
    public String parserPackage(CompilationUnit compilationUnit);
}
