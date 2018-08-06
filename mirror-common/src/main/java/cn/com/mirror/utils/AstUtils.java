package cn.com.mirror.utils;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author Piggy
 * @description
 * @since 2018年3月12日
 */
public class AstUtils {

    public static int getEndLine(ASTNode node) {
        if (null == node) return -1;

        return ((CompilationUnit) node.getRoot()).getLineNumber(
                node.getStartPosition() + node.getLength() - 1);
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
