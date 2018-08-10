package cn.com.mirror.project.unit.factory;

import cn.com.mirror.analysor.visitor.ClassVisitor;
import cn.com.mirror.analysor.visitor.VariableVisitor;
import cn.com.mirror.project.unit.Unit;
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
        Unit archive = new Unit();
        // extract all target files in s
        archive.setTargets(FileUtils.extractTargetPath(url));

        for (String targetPath : archive.getTargets()) {
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(targetPath);
            archive.addCompilationUnit(targetPath, compilationUnit);

            // packages/classes analysis
            ClassVisitor classDeclarationVisitor = new ClassVisitor(targetPath);
            compilationUnit.accept(classDeclarationVisitor);
            archive.addPackages(targetPath, classDeclarationVisitor.getPackageName());
            archive.addClasses(targetPath, classDeclarationVisitor.getClsSet());

            // variable analysis
            VariableVisitor variableVisitor = new VariableVisitor(targetPath,
                    archive.getPackages().get(targetPath), archive.getClasses());
            compilationUnit.accept(variableVisitor);
            archive.addVariables(targetPath, variableVisitor.getVariableSet());
            archive.addMappedVars(targetPath, variableVisitor.getVariableInFile());
        }

        return archive;
    }
}
