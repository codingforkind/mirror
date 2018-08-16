package cn.com.mirror.analyser.visitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.utils.AstUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.com.mirror.project.unit.element.Class;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Slf4j
public class ClassVisitor extends ASTVisitor {
    private final String file;

    private String packageName;
    // the first element in this list is the outer class, others are inner class in the first class.
    private Set<Class> clsSet = new HashSet<>();
    private Set<Method> methodSet = new HashSet<>();

    public ClassVisitor(String file) {
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
            mirrorClass.setClsStartLineNum(AstUtils.getEndLine(node));
            mirrorClass.setClsEndLineNum(AstUtils.getEndLine(node.getName()));

            if (null != typeBinding.getPackage()) {
                if (!StringUtils.isEmpty(typeBinding.getPackage().getName())) {
                    mirrorClass.setPackageName(typeBinding.getPackage().getName());
                } else {
                    mirrorClass.setPackageName(null);
                }
            }

            this.clsSet.add(mirrorClass);

            Arrays.stream(node.getMethods()).forEach(methodDeclaration -> {
                Method method = Method.instance(methodDeclaration.getName().getIdentifier(),
                        methodDeclaration.toString(),
                        AstUtils.getEndLine(methodDeclaration.getName()),
                        AstUtils.getEndLine(methodDeclaration));
                method.setMethodDeclaration(methodDeclaration);
                method.setInClass(mirrorClass);
                methodSet.add(method);
            });
        } else {
            log.error("NULL resolve binding for node: ~{}~", typeBinding);
        }

        return super.visit(node);
    }

}
