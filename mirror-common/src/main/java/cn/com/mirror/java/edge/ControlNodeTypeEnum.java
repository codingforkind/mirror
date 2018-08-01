package cn.com.mirror.java.edge;

import org.eclipse.jdt.core.dom.*;

/**
 * @author piggy
 * @description
 * @date 18-8-1
 */
public enum ControlNodeTypeEnum {
    Assert,
    Block,    // front control type node
    Break,
    ConstructorInvocation,
    Continue,
    Do,    // front control type node
    Empty,
    Expression,
    For,    // front control type node
    If,    // front control type node
    Labeled,    // front control type node
    Return,
    SuperConstructorInvocation,
    SwitchCase,    // front control type node
    Switch,    // front control type node
    Synchronized,   // ?
    Throw,
    Try,    // front control type node
    TypeDeclaration,
    VariableDeclaration,
    While,    // front control type node
    EnhancedFor;    // front control type node

    private ControlNodeTypeEnum() {

    }

    public final static boolean isControlType(Statement statement) {
        switch (getControlNodeType(statement)) {
            case Block:
            case Do:
            case For:
            case If:
            case Labeled:
            case SwitchCase:
            case Switch:
            case Try:
            case While:
            case EnhancedFor:
                return true;

            default:
                return false;
        }
    }

    public final static ControlNodeTypeEnum getControlNodeType(Statement statement) {
        if (statement instanceof AssertStatement) return Assert;
        if (statement instanceof org.eclipse.jdt.core.dom.Block) return Block;
        if (statement instanceof BreakStatement) return Break;
        if (statement instanceof org.eclipse.jdt.core.dom.ConstructorInvocation) return ConstructorInvocation;
        if (statement instanceof ContinueStatement) return Continue;
        if (statement instanceof DoStatement) return Do;
        if (statement instanceof EmptyStatement) return Empty;
        if (statement instanceof ExpressionStatement) return Expression;
        if (statement instanceof ForStatement) return For;
        if (statement instanceof IfStatement) return If;
        if (statement instanceof LabeledStatement) return Labeled;
        if (statement instanceof ReturnStatement) return Return;
        if (statement instanceof org.eclipse.jdt.core.dom.SuperConstructorInvocation) return SuperConstructorInvocation;
        if (statement instanceof org.eclipse.jdt.core.dom.SwitchCase) return SwitchCase;
        if (statement instanceof SwitchStatement) return Switch;
        if (statement instanceof SynchronizedStatement) return Synchronized;
        if (statement instanceof ThrowStatement) return Throw;
        if (statement instanceof TryStatement) return Try;
        if (statement instanceof TypeDeclarationStatement) return TypeDeclaration;
        if (statement instanceof VariableDeclarationStatement) return VariableDeclaration;
        if (statement instanceof WhileStatement) return While;
        if (statement instanceof EnhancedForStatement) return EnhancedFor;

        throw new RuntimeException("No controll type node found!");
    }


}
