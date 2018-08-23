package cn.com.mirror.project.unit.element;

import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;
import org.apache.http.util.Asserts;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Statement extends Base {
    private Set<Variable> variables;

    private Method inMethod;

    public Statement(String targetPath,
                     Integer startLineNum,
                     Integer endLineNum,
                     String content,
                     String packageName) {

        super(targetPath, startLineNum, endLineNum, content, packageName);

        this.variables = new TreeSet<>();
    }

    public void addVariable(Variable variable) {
        Asserts.notNull(variable, "Variable can not be null.");
        this.variables.add(variable);
    }


}
