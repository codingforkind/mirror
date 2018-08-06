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
            parent = parent.getParent();
        }

        // mark the relationships between astNode and statement and return
        controlEdges.put(getStartLineNum(astNode), getStartLineNum(parent));
        return parent;
    }

    private int getStartLineNum(ASTNode astNode) {
        // handle the control nodes' start line num, eg. try-catch-finally, for, enhanced for, etc.
        // TODO need to be completed
        int startLineNum = -1;

        if (astNode instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration) astNode;
            startLineNum = AstUtils.getEndLine(methodDeclaration.getName());
        }

        if (astNode instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) astNode;
            startLineNum = AstUtils.getEndLine(ifStatement.getExpression());
        }


        return startLineNum;
    }


    private boolean isControlType(ASTNode astNode) {
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

    //    AssertStatement
    @Override
    public boolean visit(AssertStatement node) {
        searchDirectParentControlNode(node);
        return super.visit(node);
    }

    //    Block
//    @Override
//    public boolean visit(Block node) {
//        searchDirectParentControlNode(node);
//        return super.visit(node);
//    }


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
//    @Override
//    public boolean visit(TypeDeclarationStatement node) {
//        return super.visit(node);
//    }


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
