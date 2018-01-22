package cn.com.cx.ps.mirror.tools.runner;

import cn.com.cx.ps.mirror.tools.visitor.PackageVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Deprecated
public class PackageAnalyzerRunner implements Runnable {

    private Map<String, CompilationUnit> prjCompUnits;
    private Map<String, String> prjPackages;

    public PackageAnalyzerRunner(Map<String, CompilationUnit> prjCompUnits) {
        this.prjCompUnits = prjCompUnits;
    }

    @Override
    public void run() {
        PackageVisitor packageVisitor = new PackageVisitor();
        Set<Map.Entry<String, CompilationUnit>> entries = prjCompUnits.entrySet();
        if (null != entries) {
            Iterator<Map.Entry<String, CompilationUnit>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, CompilationUnit> next = iterator.next();
                String key = next.getKey();
                CompilationUnit compilationUnit = next.getValue();

                compilationUnit.accept(packageVisitor);
                this.prjPackages.put(key, packageVisitor.getPackageName());
            }
        }
    }

    public Map<String, String> getPrjPackages() {
        return prjPackages;
    }
}
