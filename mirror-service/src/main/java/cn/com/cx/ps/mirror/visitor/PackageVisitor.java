package cn.com.cx.ps.mirror.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.springframework.stereotype.Component;

@Component
public class PackageVisitor extends ASTVisitor {
    private String packageName;

    @Override
    public boolean visit(PackageDeclaration node) {
        packageName = node.getName().getFullyQualifiedName();
        return super.visit(node);
    }

    public String getPackageName() {
        return packageName;
    }
}
