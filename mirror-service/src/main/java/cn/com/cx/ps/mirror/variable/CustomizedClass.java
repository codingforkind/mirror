package cn.com.cx.ps.mirror.variable;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class CustomizedClass {
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
        CustomizedClass tmObj = (CustomizedClass) obj;
//        if the typeDeclaration are equal then the class is the same one.
        if (null != typeDeclaration) {
            if (null == tmObj.getTypeDeclaration()) return false;
        } else {
            if (!typeDeclaration.equals(tmObj.getTypeDeclaration())) return false;
        }
        return true;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
