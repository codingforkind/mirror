package cn.com.mirror.analyser.visitor;

import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.utils.AstUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
public class MethodVisitor extends ASTVisitor {

    private Set<Method> methodSet = new HashSet<>();

    @Override
    public boolean visit(MethodDeclaration node) {
        Method method = new Method(node.getName().getIdentifier(),
                node.toString(),
                AstUtils.getEndLine(node.getName()),
                AstUtils.getEndLine(node));
        methodSet.add(method);
        return super.visit(node);
    }
}
