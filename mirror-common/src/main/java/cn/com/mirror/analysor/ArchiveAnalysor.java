package cn.com.mirror.analysor;

import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import cn.com.mirror.analysor.visitor.ClassVisitor;
import cn.com.mirror.analysor.visitor.VariableVisitor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class ArchiveAnalysor {

    public Unit targetAnalyze(String path) {
        // nas -> archive -> unzip -> tmppath -> analyze
        Unit archive = new Unit();
        // extract all target files in path
        archive.setTargets(FileUtils.extractTargetPath(path));

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
