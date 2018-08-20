package cn.com.mirror.analyser.visitor;

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
public class ControlEdgeVisitor extends ASTVisitor {
    private Map<Integer, Integer> controlEdges = new HashMap<>();

    private ASTNode searchDirectParentControlNode(ASTNode astNode) {
        ASTNode parent = astNode.getParent();
        while (null != parent &&
                !isControlType(parent)) {
            // locate the direct parent control type node's position
            parent = parent.getParent();
        }

        // mark the relationships between astNode and statements and return
        int currentLine = getStartLineNum(astNode);
        int directParentStartLine = getStartLineNum(parent);
        markEdges(currentLine, directParentStartLine);
        return parent;
    }

    private int getStartLineNum(ASTNode astNode) {

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
            return AstUtils.getEndLine(switchCase.getExpression());
        }

        if (astNode instanceof SwitchStatement) {
            SwitchStatement switchStatement = (SwitchStatement) astNode;
            return AstUtils.getEndLine(switchStatement.getExpression());
        }

        if (astNode instanceof TryStatement) {
            TryStatement tryStatement = (TryStatement) astNode;
            return AstUtils.getEndLine(tryStatement);
        }

        if (astNode instanceof WhileStatement) {
            WhileStatement whileStatement = (WhileStatement) astNode;
            return AstUtils.getEndLine(whileStatement.getExpression());
        }

        if (astNode instanceof EnhancedForStatement) {
            EnhancedForStatement enhancedForStatement = (EnhancedForStatement) astNode;
            return AstUtils.getEndLine(enhancedForStatement.getExpression());
        }

        if (astNode instanceof ForStatement) {
            ForStatement forStatement = (ForStatement) astNode;
            return AstUtils.getEndLine(forStatement.getExpression());
        }

        if (astNode instanceof LabeledStatement) {
            LabeledStatement labeledStatement = (LabeledStatement) astNode;
            return AstUtils.getEndLine(labeledStatement.getLabel());
        }

        if (astNode instanceof DoStatement) {
            DoStatement doStatement = (DoStatement) astNode;
            return AstUtils.getEndLine(doStatement);
        }

        return AstUtils.getEndLine(astNode);
    }


    /**
     * check the control type node in a method
     */
    private boolean isControlType(ASTNode astNode) {
        // is astNode in a control type node, this method will return directly
        // if (x && y){}, x and y are in the control type of if node, this method
        // will re turn directly and cant find x and y's direct control edge
        // TODO xyz FIX BUG control edge

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
