package cn.com.cx.ps.mirror.common.visitor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.com.cx.ps.mirror.java.variable.Class;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDeclarationVisitor extends ASTVisitor {
	private Logger log = LoggerFactory.getLogger(getClass());
	private String file;
	// the first element in this list is the outer class, others are inner class in the first class.
	private Set<Class> prjClasses = new HashSet<>();

	public ClassDeclarationVisitor(String file) {
		this.file = file;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding typeBinding = node.resolveBinding();
		if (null != typeBinding) {
			// log.info("class name: {}", typeBinding.getQualifiedName());
			Class mirrorClass = new Class();
			mirrorClass.setFile(this.file);
			mirrorClass.setInterface(node.isInterface());
			mirrorClass.setName(node.getName().getIdentifier());
			mirrorClass.setTypeDeclaration(node);
			mirrorClass.setQualifiedName(typeBinding.getQualifiedName());

			if (null != typeBinding.getPackage()) {
				if (!StringUtils.isEmpty(typeBinding.getPackage().getName())) {
					mirrorClass.setPackageName(typeBinding.getPackage().getName());
				} else {
					mirrorClass.setPackageName(null);
				}
			}

			this.prjClasses.add(mirrorClass);
		} else {
			log.error("NULL resolve binding for node: ~{}~", typeBinding);
		}

		return super.visit(node);
	}

}
