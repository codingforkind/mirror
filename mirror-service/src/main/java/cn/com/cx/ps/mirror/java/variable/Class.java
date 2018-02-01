package cn.com.cx.ps.mirror.java.variable;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import lombok.Data;

@Data
public class Class {
    private String file;
    private String name;
    private String qualifiedName;
    private String packageName;
    private TypeDeclaration typeDeclaration;
    private boolean isInterface;
    private boolean isPublic;
    private boolean isProtected;
    private boolean isPrivate;
    private boolean isDefault;
    private boolean isFinal;

    @Override
    public int hashCode() {
        if (null == typeDeclaration) return 0;
        return this.typeDeclaration.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Class tmObj = (Class) obj;
//        if the typeDeclaration are equal then the class is the same one.
        if (null != typeDeclaration) {
            if (null == tmObj.getTypeDeclaration()) return false;
        } else {
            if (!typeDeclaration.equals(tmObj.getTypeDeclaration())) return false;
        }
        return true;
    }

}
