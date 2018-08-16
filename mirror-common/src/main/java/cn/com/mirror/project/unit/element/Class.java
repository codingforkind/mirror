package cn.com.mirror.project.unit.element;

import cn.com.mirror.utils.BeanUtils;
import lombok.Data;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.Objects;

@Data
public class Class extends Base {
    private static final long serialVersionUID = 1L;
    private String name;
    private String qualifiedName;
    private TypeDeclaration typeDeclaration;
    private boolean isInterface;
    private boolean isPublic;
    private boolean isProtected;
    private boolean isPrivate;
    private boolean isDefault;
    private boolean isFinal;

    public Class(String targetPath,
                 Integer startLineNum,
                 Integer endLineNum,
                 String content,
                 String packageName,
                 String name,
                 String qualifiedName,
                 TypeDeclaration typeDeclaration) {

        super(targetPath, startLineNum, endLineNum, content, packageName);

        this.name = name;
        this.qualifiedName = qualifiedName;
        this.typeDeclaration = typeDeclaration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeDeclaration, this.getTargetPath());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Class that = (Class) obj;
        return Objects.equals(typeDeclaration, that.getTypeDeclaration()) &&
                Objects.equals(this.getTargetPath(), that.getTargetPath());
    }

}
