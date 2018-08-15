package cn.com.mirror.constant;

import org.eclipse.jdt.core.dom.*;

/**
 * @author piggy
 * @description
 * @date 18-8-1
 */
public enum ControlNodeTypeEnum {
    ASSERT,
//    BLOCK,    // front control type node
    BREAK,
    CONSTRUCTOR_INVOCATION,
    CONTINUE,
    DO,    // front control type node
    EMPTY,
    EXPRESSION,
    FOR,    // front control type node
    IF,    // front control type node
    LABELED,    // front control type node
    RETURN,
    SUPER_CONSTRUCTOR_INVOCATION,
    SWITCH_CASE,    // front control type node
    SWITCH,    // front control type node
    SYNCHRONIZED,   // ?
    THROW,
    TRY,    // front control type node
    TYPE_DECLARATION,    // front control type node
    VARIABLE_DECLARATION,
    WHILE,    // front control type node
    ENHANCED_FOR,    // front control type node

    UNKNOWN_TYPE;

    private ControlNodeTypeEnum() {

    }

    public final static ControlNodeTypeEnum getControlNodeType(Statement statement) {
        if (statement instanceof AssertStatement) return ASSERT;
//        if (statements instanceof org.eclipse.jdt.core.dom.Block) return BLOCK;
        if (statement instanceof BreakStatement) return BREAK;
        if (statement instanceof org.eclipse.jdt.core.dom.ConstructorInvocation) return CONSTRUCTOR_INVOCATION;
        if (statement instanceof ContinueStatement) return CONTINUE;
        if (statement instanceof DoStatement) return DO;
        if (statement instanceof EmptyStatement) return EMPTY;
        if (statement instanceof ExpressionStatement) return EXPRESSION;
        if (statement instanceof ForStatement) return FOR;
        if (statement instanceof IfStatement) return IF;
        if (statement instanceof LabeledStatement) return LABELED;
        if (statement instanceof ReturnStatement) return RETURN;
        if (statement instanceof org.eclipse.jdt.core.dom.SuperConstructorInvocation)
            return SUPER_CONSTRUCTOR_INVOCATION;
        if (statement instanceof org.eclipse.jdt.core.dom.SwitchCase) return SWITCH_CASE;
        if (statement instanceof SwitchStatement) return SWITCH;
        if (statement instanceof SynchronizedStatement) return SYNCHRONIZED;
        if (statement instanceof ThrowStatement) return THROW;
        if (statement instanceof TryStatement) return TRY;
        if (statement instanceof TypeDeclarationStatement) return TYPE_DECLARATION;
        if (statement instanceof VariableDeclarationStatement) return VARIABLE_DECLARATION;
        if (statement instanceof WhileStatement) return WHILE;
        if (statement instanceof EnhancedForStatement) return ENHANCED_FOR;

        return UNKNOWN_TYPE;
    }


}
