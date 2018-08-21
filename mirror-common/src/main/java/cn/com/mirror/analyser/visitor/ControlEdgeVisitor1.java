package cn.com.mirror.analyser.visitor;

import cn.com.mirror.constant.ControlNodeTypeEnum;
import cn.com.mirror.utils.AstUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-2
 */
@Slf4j
@Getter
public class ControlEdgeVisitor1 extends ASTVisitor {
    private Map<Integer, Integer> controlEdges = new HashMap<>();

    private ASTNode searchDirectParentControlNode(ASTNode astNode) {
        ASTNode parent = astNode.getParent();
        while (null != parent &&
                !isControlType(parent)) {
            // TODO xyz bug fix may be visit the statements instead
            // locate the direct parent control type node's position
            parent = parent.getParent();
        }

        // mark the s between astNode and statements and return
        int currentLine = AstUtils.getSpecificStartLine(astNode);
        int directParentStartLine = AstUtils.getSpecificStartLine(parent);
        markEdges(currentLine, directParentStartLine);
        return parent;
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
        int typeDecLine = AstUtils.getStartLine(node.getName());
        Arrays.stream(node.getFields()).forEach(fieldDeclaration -> {
            markEdges(AstUtils.getSpecificStartLine(fieldDeclaration.getType()), typeDecLine);
        });

        Arrays.stream(node.getMethods()).forEach(methodDeclaration -> {
            markEdges(AstUtils.getSpecificStartLine(methodDeclaration.getName()), typeDecLine);
        });

        return super.visit(node);
    }
}
