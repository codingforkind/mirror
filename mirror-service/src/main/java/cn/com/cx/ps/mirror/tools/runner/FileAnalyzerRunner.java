package cn.com.cx.ps.mirror.tools.runner;

import cn.com.cx.ps.mirror.utils.AstUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Analyzing file infos for the prj
 */
public class FileAnalyzerRunner implements Runnable {

    private Set<String> prjJavaFiles;
    private Map<String, List<String>> prjCodeLines;
    private Map<String, CompilationUnit> prjCompUnits;


    public FileAnalyzerRunner(Set<String> prjJavaFiles) {
        this.prjJavaFiles = prjJavaFiles;
    }

    @Override
    public void run() {
        for (String tmPath : this.prjJavaFiles) {
            this.prjCodeLines.put(tmPath, AstUtils.listCodeLines(tmPath));
            this.prjCompUnits.put(tmPath, AstUtils.getCompUnitResolveBinding(tmPath));
        }
    }

    public Map<String, List<String>> getPrjCodeLines() {
        return prjCodeLines;
    }

    public Map<String, CompilationUnit> getPrjCompUnits() {
        return prjCompUnits;
    }
}
