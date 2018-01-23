package cn.com.cx.ps.mirror.common.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClassVisitor extends ASTVisitor {
    @Override
    public boolean visit(TypeDeclaration node) {
//        TODO visit all the classes and interfaces defined in this project and preserve it in static way.
//        node.getSuperclassType();
//        mark the parent type of this node.
//        node.isInterface()
//        node.superInterfaceTypes()

        return super.visit(node);
    }
}
