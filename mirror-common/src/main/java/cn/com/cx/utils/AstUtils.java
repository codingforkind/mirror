package cn.com.cx.utils;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * Created by Piggy on 2017/7/20.
 */
public class AstUtils {

    public static int getEndLine(ASTNode node) {
        return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition() + node.getLength() - 1);
    }

    public static final CompilationUnit getCompUnitResolveBinding(String javaFile) {
        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        astParser.setEnvironment(null, null, null, true);
        astParser.setUnitName(FileUtils.getFileName(javaFile));
        astParser.setResolveBindings(true);
        astParser.setBindingsRecovery(true);
        String content = FileUtils.getFileContent(javaFile);
        astParser.setSource(content.toCharArray());

        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
        return result;
    }

}
