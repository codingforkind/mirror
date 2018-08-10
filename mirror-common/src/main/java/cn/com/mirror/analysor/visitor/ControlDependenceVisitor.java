package cn.com.mirror.analysor.visitor;

import cn.com.mirror.constant.ControlNodeTypeEnum;
import cn.com.mirror.utils.AstUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-2
 */
@Slf4j
@Getter
public class ControlDependenceVisitor extends ASTVisitor {
    private Map<Integer, Integer> controlEdges = new HashMap<>();

    private ASTNode searchDirectParentControlNode(ASTNode astNode) {
        ASTNode parent = astNode.getParent();
        while (null != parent &&
                !isControlType(parent)) {
            // locate the direct parent control type node's position
            parent = parent.getParent();
        }

        // mark the relationships between astNode and statement and return
        int currentLine = getStartLineNum(astNode);
        int directParentStartLine = getStartLineNum(parent);
        markEdges(currentLine, directParentStartLine);
        return parent;
    }

    private int getStartLineNum(ASTNode astNode) {
        // handle the control nodes' start line num, eg. try-catch-finally, for, enhanced for, etc.
        // TODO need to be completed, be aware multi lines
        /**
         * the control dependence might have some edges points to the other direction,
         * like try and do type, which are might have some dependence point at 'catch-finally'
         * and 'while' part
         */

        if (astNode instanceof TypeDeclaration) {
            TypeDeclaration typeDeclaration = (TypeDeclaration) astNode;
            return AstUtils.getEndLine(typeDeclaration.getName());
        }

        if (astNode instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) astNode;

            int methodStartLine = AstUtils.getEndLine(methodDeclaration.getName());
            return methodStartLine;
        }

        if (astNode instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) astNode;
            return AstUtils.getEndLine(ifStatement.getExpression());
        }

        if (astNode instanceof SwitchCase) {
            SwitchCase switchCase = (SwitchCase) astNode;
            // be aware multi lines
            return AstUtils.getEndLine(switchCase.getExpression());
        }

        if (astNode instanceof SwitchStatement) {
            SwitchStatement switchStatement = (SwitchStatement) astNode;
            // be aware multi lines
            return AstUtils.getEndLine(switchStatement.getExpression());
        }

        if (astNode instanceof TryStatement) {
            TryStatement tryStatement = (TryStatement) astNode;
            // TODO special treatment for this type of control node, the dependence of the nodes can also have some
            // edges heading for catch part.
            if (null != tryStatement.getBody()) {
                List<Statement> statements = tryStatement.getBody().statements();
            }
            return AstUtils.getEndLine(tryStatement);
        }

        if (astNode instanceof WhileStatement) {
            WhileStatement whileStatement = (WhileStatement) astNode;
            // be aware multi lines
            return AstUtils.getEndLine(whileStatement.getExpression());
        }

        if (astNode instanceof EnhancedForStatement) {
            EnhancedForStatement enhancedForStatement = (EnhancedForStatement) astNode;
            // be aware multi lines
            return AstUtils.getEndLine(enhancedForStatement.getExpression());
        }

        if (astNode instanceof ForStatement) {
            ForStatement forStatement = (ForStatement) astNode;
            // be aware multi lines
            return AstUtils.getEndLine(forStatement.getExpression());
        }

        if (astNode instanceof LabeledStatement) {
            LabeledStatement labeledStatement = (LabeledStatement) astNode;
            return AstUtils.getEndLine(labeledStatement.getLabel());
        }

        if (astNode instanceof DoStatement) {
            DoStatement doStatement = (DoStatement) astNode;
            // TODO like the try type control node, the dependence of nodes might have some edges heading for
            // while part.
            return AstUtils.getEndLine(doStatement);
        }

        return AstUtils.getEndLine(astNode);
    }


    /**
     * check the control type node in a method
     */
    private boolean isControlType(ASTNode astNode) {

        if (astNode instanceof MethodDeclaration) return true;

        if (astNode instanceof Statement) {
            Statement statement = (Statement) astNode;

            switch (ControlNodeTypeEnum.getControlNodeType(statement)) {
                case IF:
                case SWITCH_CASE:
                case SWITCH:
                case TRY:
                case WHILE:
                case ENHANCED_FOR:
                case FOR:
                case LABELED:
                case DO:
                    return true;

                default:
                    return false;
            }
        }

        return false;

    }

    private boolean markEdges(int curLine, int parentLine) {
        if (-1 != parentLine &&
                curLine != parentLine) {
            controlEdges.put(curLine, parentLine);
            return true;
        }

        return false;
    }

    @Override
    public boolean visit(SimpleName node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        int typeDecLine = AstUtils.getEndLine(node.getName());
        Arrays.stream(node.getFields()).forEach(fieldDeclaration -> {
            markEdges(getStartLineNum(fieldDeclaration.getType()), typeDecLine);
        });

        Arrays.stream(node.getMethods()).forEach(methodDeclaration -> {
            markEdges(getStartLineNum(methodDeclaration.getName()), typeDecLine);
        });

        return super.visit(node);
    }
}
