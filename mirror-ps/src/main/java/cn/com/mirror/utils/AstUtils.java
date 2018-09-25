package cn.com.mirror.utils;

import org.eclipse.jdt.core.dom.*;

/**
 * @author Piggy
 * @description
 */
public class AstUtils {

    public static int getStartLine(ASTNode node) {
//        Using the first two character to calculate the line number
        return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition() + 1);
    }


    public static int getEndLine(ASTNode node) {
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


    /**
     * Get specific statement (or TypeDeclaration, MethodDeclaration) start line number
     *
     * @param astNode Statement, TypeDeclaration, MethodDeclaration
     */
    public static final int getSpecificStartLine(ASTNode astNode) {

        if (astNode instanceof TypeDeclaration) {
            TypeDeclaration typeDeclaration = (TypeDeclaration) astNode;
            return getStartLine(typeDeclaration.getName());
        }

        if (astNode instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) astNode;

            int methodStartLine = getStartLine(methodDeclaration.getName());
            return methodStartLine;
        }

        if (astNode instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) astNode;
            return getStartLine(ifStatement.getExpression());
        }

        if (astNode instanceof SwitchCase) {
            SwitchCase switchCase = (SwitchCase) astNode;
            // the expression of default case is null
            if (null == switchCase.getExpression()) {
                return getStartLine(switchCase);
            }

            return getStartLine(switchCase.getExpression());
        }

        if (astNode instanceof SwitchStatement) {
            SwitchStatement switchStatement = (SwitchStatement) astNode;
            return getStartLine(switchStatement.getExpression());
        }

        if (astNode instanceof TryStatement) {
            TryStatement tryStatement = (TryStatement) astNode;
            return getStartLine(tryStatement);
        }

        if (astNode instanceof WhileStatement) {
            WhileStatement whileStatement = (WhileStatement) astNode;
            return getStartLine(whileStatement.getExpression());
        }

        if (astNode instanceof EnhancedForStatement) {
            EnhancedForStatement enhancedForStatement = (EnhancedForStatement) astNode;
            return getStartLine(enhancedForStatement.getExpression());
        }

        if (astNode instanceof ForStatement) {
            ForStatement forStatement = (ForStatement) astNode;
            return getStartLine(forStatement.getExpression());
        }

        if (astNode instanceof LabeledStatement) {
            LabeledStatement labeledStatement = (LabeledStatement) astNode;
            return getStartLine(labeledStatement.getLabel());
        }

        if (astNode instanceof DoStatement) {
            DoStatement doStatement = (DoStatement) astNode;
            return getStartLine(doStatement);
        }

        return getStartLine(astNode);
    }


}
