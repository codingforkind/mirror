package cn.com.mirror.project.unit.element;

import cn.com.mirror.constant.ElementTypeEnum;
import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private Set<Variable> fields;

    public Class(String targetPath,
                 Integer startLineNum,
                 Integer endLineNum,
                 String content,
                 String packageName,
                 String name,
                 String qualifiedName,
                 TypeDeclaration typeDeclaration) {

        super(targetPath, startLineNum, endLineNum, content, packageName, ElementTypeEnum.CLASS);

        this.name = name;
        this.qualifiedName = qualifiedName;
        this.typeDeclaration = typeDeclaration;

        this.fields = new HashSet<>();
    }

    public void addField(Variable field) {
        Asserts.notNull(field, "Field variable can not be null.");
        this.fields.add(field);
    }

    public Class getCls(TypeDeclaration typeDeclaration) {
        if (this.typeDeclaration.equals(typeDeclaration)) {
            return this;
        }
        return null;
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
