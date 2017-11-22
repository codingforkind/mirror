package cn.com.cx.ps.analyzer;

import cn.com.cx.ps.utils.AstUtils;
import cn.com.cx.ps.variable.CustomizedClass;
import cn.com.cx.ps.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.visitor.ClassVisitor;
import cn.com.cx.ps.visitor.PackageVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.*;

/**
 * Analyzing class infos for the prj
 */
public class ClassAnalyzer implements Runnable {
    private Map<String, CompilationUnit> prjCompUnits;
    private Map<String, Set<CustomizedClass>> prjClasses;

    public ClassAnalyzer(Map<String, CompilationUnit> prjCompUnits) {
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

    public Map<String, Set<CustomizedClass>> getPrjClasses() {
        return prjClasses;
    }
}
