package cn.com.mirror.project.unit;

import cn.com.mirror.analyser.visitor.ClassVisitor;
import cn.com.mirror.analyser.visitor.VariableVisitor;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
public class UnitFactory {

    public Unit newUnit(String url) {
        // nas -> archive -> unzip -> tmppath -> analyze
        Unit unit = new Unit();
        // extract all target files in s
        unit.setTargets(FileUtils.extractTargetPath(url));

        for (String targetPath : unit.getTargets()) {
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(targetPath);
            unit.addCompilationUnit(targetPath, compilationUnit);

            // packages/classes analysis
            ClassVisitor classDeclarationVisitor = new ClassVisitor(targetPath);
            compilationUnit.accept(classDeclarationVisitor);
            unit.addPackages(targetPath, classDeclarationVisitor.getPackageName());
            unit.addClasses(targetPath, classDeclarationVisitor.getClsSet());
            unit.addMethods(targetPath, classDeclarationVisitor.getMethodSet());

            // element analysis
            VariableVisitor variableVisitor = new VariableVisitor(targetPath,
                    unit.getPackages().get(targetPath), unit.getClasses(), unit.getMethods().get(targetPath));
            compilationUnit.accept(variableVisitor);
            unit.addVariables(targetPath, variableVisitor.getVariableSet());
            unit.addMappedVars(targetPath, variableVisitor.getVariableInFile());
        }

        return unit;
    }
}
