package cn.com.mirror.project.unit.element;

import cn.com.mirror.constant.ElementTypeEnum;
import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;
import org.apache.commons.lang3.Validate;

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

    public Statement(String targetPath,
                     Integer startLineNum,
                     Integer endLineNum,
                     String content,
                     String packageName) {

        super(targetPath, startLineNum, endLineNum, content, packageName, ElementTypeEnum.STATEMENT);

        this.variables = new TreeSet<>();
    }

    public void addVariable(Variable variable) {
        Validate.notNull(variable, "Variable can not be null.");
        this.variables.add(variable);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
