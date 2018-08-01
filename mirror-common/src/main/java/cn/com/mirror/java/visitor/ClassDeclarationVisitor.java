package cn.com.mirror.java.visitor;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.com.mirror.java.variable.Class;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Slf4j
public class ClassDeclarationVisitor extends ASTVisitor {
    private final String file;

    private String packageName;
    // the first element in this list is the outer class, others are inner class in the first class.
    private Set<Class> clsSet = new HashSet<>();

    public ClassDeclarationVisitor(String file) {
        this.file = file;
    }

    @Override
    public boolean visit(PackageDeclaration node) {
        packageName = node.getName().getFullyQualifiedName();
        return super.visit(node);
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

            this.clsSet.add(mirrorClass);
        } else {
            log.error("NULL resolve binding for node: ~{}~", typeBinding);
        }

        return super.visit(node);
    }

}
