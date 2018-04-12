package cn.com.cx.ps.mirror.common.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import lombok.Getter;

@Getter
public class PackageVisitor extends ASTVisitor {
	private String packageName;

	@Override
	public boolean visit(PackageDeclaration node) {
		packageName = node.getName().getFullyQualifiedName();
		return super.visit(node);
	}
}
