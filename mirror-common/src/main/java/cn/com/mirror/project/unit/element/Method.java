package cn.com.mirror.project.unit.element;

import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private Set<Variable> params;

    public Method(String targetPath,
                  Integer startLineNum,
                  Integer endLineNum,
                  String content,
                  String packageName,
                  String name,
                  MethodDeclaration methodDeclaration) {

        super(targetPath, startLineNum, endLineNum, content, packageName);

        this.name = name;
        this.methodDeclaration = methodDeclaration;

        this.params = new HashSet<>();
    }

    public void addParam(Variable param){
        Asserts.notNull(param, "Parameter variable can not be null.");
        this.params.add(param);
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
