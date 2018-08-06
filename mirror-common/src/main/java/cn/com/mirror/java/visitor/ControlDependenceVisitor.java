package cn.com.mirror.java.visitor;

import cn.com.mirror.java.edge.ControlNodeTypeEnum;
import cn.com.mirror.utils.AstUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
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
        while (!isControlType(parent)) {
            // locate the direct parent control type node's position
            if (null == parent) break;

            parent = parent.getParent();
        }

        // mark the relationships between astNode and statement and return
        controlEdges.put(getStartLineNum(astNode), getStartLineNum(parent));
        return parent;
    }

    private int getStartLineNum(ASTNode astNode) {
        // handle the control nodes' start line num, eg. try-catch-finally, for, enhanced for, etc.
        // TODO need to be completed

        if (astNode instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) astNode;
            return AstUtils.getEndLine(methodDeclaration.getName());
        }

        if (astNode instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) astNode;
            return AstUtils.getEndLine(ifStatement.getExpression());
        }

        return AstUtils.getEndLine(astNode);
    }


    private boolean isControlType(ASTNode astNode) {
        if (null == astNode) return false;

        if (astNode instanceof MethodDeclaration) return true;

        if (astNode instanceof Statement) {
            Statement statement = (Statement) astNode;

            switch (ControlNodeTypeEnum.getControlNodeType(statement)) {
                case BLOCK:
                case DO:
                case FOR:
                case IF:
                case LABELED:
                case SWITCH_CASE:
                case SWITCH:
                case TRY:
                case WHILE:
                case ENHANCED_FOR:
                    return true;

                default:
                    return false;
            }
        }

        return false;

    }

    @Override
    public boolean visit(SimpleName node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }
}
