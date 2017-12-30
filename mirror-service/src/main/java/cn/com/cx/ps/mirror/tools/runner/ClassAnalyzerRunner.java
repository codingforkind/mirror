package cn.com.cx.ps.mirror.tools.runner;

import cn.com.cx.ps.mirror.project.variable.Class;
import cn.com.cx.ps.mirror.tools.visitor.ClassDeclarationVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Analyzing class infos for the prj
 */
public class ClassAnalyzerRunner implements Runnable {
    private Map<String, CompilationUnit> prjCompUnits;
    private Map<String, Set<Class>> prjClasses;

    public ClassAnalyzerRunner(Map<String, CompilationUnit> prjCompUnits) {
        this.prjCompUnits = prjCompUnits;
    }

    @Override
    public void run() {
        ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        Set<Map.Entry<String, CompilationUnit>> entries = prjCompUnits.entrySet();
        if (null != entries) {
            Iterator<Map.Entry<String, CompilationUnit>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, CompilationUnit> next = iterator.next();
                String key = next.getKey();
                CompilationUnit compilationUnit = next.getValue();

                compilationUnit.accept(classDeclarationVisitor);
                this.prjClasses.put(key, classDeclarationVisitor.getCustomizedClasses());
            }
        }
    }

    public Map<String, Set<Class>> getPrjClasses() {
        return prjClasses;
    }
}
