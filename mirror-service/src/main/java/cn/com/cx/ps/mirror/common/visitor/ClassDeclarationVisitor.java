package cn.com.cx.ps.mirror.common.visitor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.java.variable.Class;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDeclarationVisitor extends ASTVisitor {
	private Logger log = LoggerFactory.getLogger(getClass());
	private String file;
	// the first element in this list is the outer class, others are inner class in
	// the first class.
	private Set<Class> prjClasses = new HashSet<>();

	public ClassDeclarationVisitor(String file) {
		this.file = file;
	}

	// TODO 开始优化特殊情况特殊处理
	// TODO 内部类需要处理
	// TODO TYPE: test.GeneraticClass<String,TestClass> 泛型有待分析
	/*if (!node.typeParameters().isEmpty()) {
		builder.append("<");
		for (Object obj : node.typeParameters()) {
			if (obj instanceof TypeParameter) {
				TypeParameter parameter = (TypeParameter) obj;
				builder.append(parameter.getName());
				builder.append(",");
			}
		}
	}
	builder.delete(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1);
	builder.append(">");
	log.info("Full class name: {}", builder.toString());*/
	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding typeBinding = node.resolveBinding();
		if (null != typeBinding) {
			log.info("class name: {}", typeBinding.getQualifiedName());
			Class customizedClass = new Class();
			customizedClass.setFile(this.file);
			customizedClass.setInterface(node.isInterface());
			customizedClass.setName(node.getName().getIdentifier());
			customizedClass.setTypeDeclaration(node);
			customizedClass.setQualifiedName(typeBinding.getQualifiedName());

			if (null != typeBinding.getPackage()) {
				if (!typeBinding.getPackage().getName().equals("")) {
					customizedClass.setPackageName(typeBinding.getPackage().getName());
				} else {
					customizedClass.setPackageName(null);
				}
			}
			
			this.prjClasses.add(customizedClass);
		} else {
			log.error("NULL resolve binding for node: ~{}~", typeBinding);
		}
		
		return super.visit(node);
	}


}
