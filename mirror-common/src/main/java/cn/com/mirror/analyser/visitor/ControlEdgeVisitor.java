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
 * TODO xyz what the conception of control dependence is?
 * return statement control dependence on the start line of the method or the the statement direct above itself.
 * break statement control dependence on the start of the block or the the statement direct above itself.
 *
 *
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

        if (astNode instanceof MethodDeclaration) return true;

        if (astNode instanceof Statement) {
            Statement statement = (Statement) astNode;

            switch (ControlNodeTypeEnum.getControlNodeType(statement)) {
                case IF: // TODO xyz ERROR the statements in else block control dependence on if
                case SWITCH_CASE: // TODO xyz expressionStatement is not switch_case statement's child, fix this
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

//    @Override
//    public boolean visit(SimpleName node) {
//        searchDirectParentControlNode(node);
//        return super.visit(node);
//    }

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


    //    AssertStatement
    @Override
    public boolean visit(AssertStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }

    //    Block
    @Override
    public boolean visit(Block node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    BreakStatement
    @Override
    public boolean visit(BreakStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    ConstructorInvocation
    @Override
    public boolean visit(ConstructorInvocation node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    ContinueStatement
    @Override
    public boolean visit(ContinueStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    DoStatement
    @Override
    public boolean visit(DoStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    EmptyStatement
//    @Override
//    public boolean visit(EmptyStatement node) {
//        return super.visit(node);
//    }


    //    ExpressionStatement
    @Override
    public boolean visit(ExpressionStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    ForStatement
    @Override
    public boolean visit(ForStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    IfStatement
    @Override
    public boolean visit(IfStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    LabeledStatement
    @Override
    public boolean visit(LabeledStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    ReturnStatement
    @Override
    public boolean visit(ReturnStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    SuperConstructorInvocation
    @Override
    public boolean visit(SuperConstructorInvocation node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    SwitchCase
    @Override
    public boolean visit(SwitchCase node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    SwitchStatement
    @Override
    public boolean visit(SwitchStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    SynchronizedStatement
    @Override
    public boolean visit(SynchronizedStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    ThrowStatement
    @Override
    public boolean visit(ThrowStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    TryStatement
    @Override
    public boolean visit(TryStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    TypeDeclarationStatement
    @Override
    public boolean visit(TypeDeclarationStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    VariableDeclarationStatement
    @Override
    public boolean visit(VariableDeclarationStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }


    //    WhileStatement
    @Override
    public boolean visit(WhileStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }

    //    EnhancedForStatement
    @Override
    public boolean visit(EnhancedForStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }
}
