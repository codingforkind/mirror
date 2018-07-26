package cn.com.mirror.analysor;

import cn.com.mirror.project.Archive;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import cn.com.mirror.visitor.ClassDeclarationVisitor;
import cn.com.mirror.visitor.VariableVisitor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class ArchiveAnalysor {

    public Archive targetAnalyze(String path) {
        // nas -> archive -> unzip -> tmppath -> analyze
        Archive archive = new Archive();
        // extract all target files in path
        archive.setTargets(FileUtils.extractTargetPath(path));

        for (String targetPath : archive.getTargets()) {
            log.debug("Target path: {}", targetPath);
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(targetPath);

            // packages/classes analysis
            ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(targetPath);
            compilationUnit.accept(classDeclarationVisitor);
            archive.addPackages(targetPath, classDeclarationVisitor.getPackageName());
            archive.addClasses(targetPath, classDeclarationVisitor.getClsSet());

            // variable analysis
            VariableVisitor variableVisitor = new VariableVisitor(targetPath,
                    archive.getPackages().get(targetPath), archive.getClasses());
            compilationUnit.accept(variableVisitor);
            archive.addVariables(targetPath, variableVisitor.getVariableSet());
        }

        return archive;
    }

}
