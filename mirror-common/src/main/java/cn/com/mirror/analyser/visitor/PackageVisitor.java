package cn.com.mirror.analyser.visitor;

import cn.com.mirror.project.unit.element.Root;
import lombok.Getter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;

/**
 * @author piggy
 * @description
 * @date 18-8-26
 */
@Getter
public class PackageVisitor extends ASTVisitor {
    private String file;

    private Root root;

    public PackageVisitor(String file) {
        this.file = file;
    }

    @Override
    public boolean visit(PackageDeclaration node) {
        this.root = new Root(this.file,
                1,
                1,
                node.toString(),
                node.getName().getFullyQualifiedName());

        return super.visit(node);
    }

}
