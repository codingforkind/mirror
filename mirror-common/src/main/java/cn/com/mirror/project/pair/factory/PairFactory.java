package cn.com.mirror.project.pair.factory;

import cn.com.mirror.analyser.visitor.ControlEdgeVisitor;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
public class PairFactory {

    public Pair newPair(String repositoryUrl) {
        Pair pair = new Pair();

        Set<String> targetFiles = FileUtils.extractTargetPath(repositoryUrl);
        targetFiles.stream().forEach(targetPath -> {
            ControlEdgeVisitor controlEdgeVisitor = new ControlEdgeVisitor();
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(targetPath);
            compilationUnit.accept(controlEdgeVisitor);
            pair.addDirectCtrlEdges(targetPath, controlEdgeVisitor.getControlEdges());
        });

        return pair;
    }
}
