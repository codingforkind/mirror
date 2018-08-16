package cn.com.mirror.project.unit.element;

import lombok.Data;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Method extends Base {
    private static final long serialVersionUID = 1L;

    private String name;
    private MethodDeclaration methodDeclaration;

    private Class inClass;

    public Method(String targetPath,
                  Integer startLineNum,
                  Integer endLineNum,
                  String content,
                  String packageName,
                  String name,
                  MethodDeclaration methodDeclaration,
                  Class inClass) {

        super(targetPath, startLineNum, endLineNum, content, packageName);

        this.name = name;
        this.methodDeclaration = methodDeclaration;
        this.inClass = inClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;
        return Objects.equals(name, method.name) &&
                Objects.equals(this.getContent(), method.getContent()) &&
                Objects.equals(this.getStartLineNum(), method.getStartLineNum()) &&
                Objects.equals(this.getEndLineNum(), method.getEndLineNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, this.getContent(), this.getStartLineNum(), this.getEndLineNum());
    }
}
