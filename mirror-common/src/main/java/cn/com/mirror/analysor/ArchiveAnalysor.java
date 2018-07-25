package cn.com.mirror.analysor;

import cn.com.mirror.project.Archive;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import cn.com.mirror.visitor.ClassDeclarationVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
public class ArchiveAnalysor {

    public Archive targetAnalyze(String path) {
        // nas -> archive -> unzip -> tmppath -> analyze
        Archive archive = new Archive();
        archive.setTargets(FileUtils.extractTargetPath(path));

        for (String targetPath : archive.getTargets()) {
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(targetPath);
            ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(targetPath);
            compilationUnit.accept(classDeclarationVisitor);

            archive.addClasses(targetPath, classDeclarationVisitor.getClsSet());
        }

        return archive;
    }

}
