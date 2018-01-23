package cn.com.cx.ps.mirror.visitor;

import org.eclipse.jdt.core.dom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.java.variable.Class;

import java.util.HashSet;
import java.util.Set;

public class ClassDeclarationVisitor extends ASTVisitor {
    private static Logger log = LoggerFactory.getLogger(ClassDeclarationVisitor.class);
    private String file;
    //    the first element in this list is the outer class, others are inner class in the first class.
    private Set<Class> customizedClasses = new HashSet<>();

//    TODO $ other type of declaration!!!!!
    @Override
    public boolean visit(TypeDeclaration node) {
        Class customizedClass = new Class();
        customizedClass.setFile(this.file);
        customizedClass.setInterface(node.isInterface());
        customizedClass.setName(String.valueOf(node.getName()));
        customizedClass.setTypeDeclaration(node);

        ITypeBinding typeBinding = node.resolveBinding();
        try {
            if (null != typeBinding) {
                log.info("class name: {}", typeBinding.getQualifiedName());
//                TYPE: test.GeneraticClass<String,TestClass>, the analyze is not complete
                StringBuilder builder = new StringBuilder(typeBinding.getQualifiedName());
//                customizedClass.setQualifiedName(typeBinding.getQualifiedName());
                if(!node.typeParameters().isEmpty()){
                    builder.append("<");
                    for(Object obj : node.typeParameters()){
                        if(obj instanceof TypeParameter){
                            TypeParameter parameter = (TypeParameter)obj;
                            builder.append(parameter.getName());
                            builder.append(",");
                        }
                    }
                }
                builder.delete(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1);
                builder.append(">");
                log.info("Full class name: {}", builder.toString());
                customizedClass.setQualifiedName(builder.toString());
                if (null != typeBinding.getPackage()) {
                    if (!typeBinding.getPackage().getName().equals("")) {
                        customizedClass.setPackageName(typeBinding.getPackage().getName());
                    } else {
                        customizedClass.setPackageName(null);
                    }
                }
            } else {
                throw new Exception("Resolve binding is null!!!!!!!");
            }
        } catch (Exception e) {
//            EXCEPTION HANDLER
        } finally {
        }
        this.customizedClasses.add(customizedClass);
        return super.visit(node);
    }

    public Set<Class> getCustomizedClasses() {
        return customizedClasses;
    }
}
