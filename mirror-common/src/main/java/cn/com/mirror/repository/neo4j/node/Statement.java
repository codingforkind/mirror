/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.utils.BeanUtils;
import cn.com.mirror.project.unit.variable.Variable;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Piggy
 * @description
 */
@Getter
@Setter
@NodeEntity(label = "a line of code in target file")
public class Statement extends Method {
    private static final long serialVersionUID = 1L;

    @Property(name = "statement content")
    private String statementContent;

    @Property(name = "current statement belongs to this method")
    @Relationship(type = EdgeType.EDGE_TYPE.STATEMENT_TO_METHOD_CTRL_EDGE)
    private Method method;

    private Set<Variable> variables = new HashSet<>();

    Statement(ASTNode statementContent,
              Set<Variable> variables) {

        this.statementContent = statementContent.toString();
        this.variables = variables;
    }

    public static Statement instance(Method method,
                                     ASTNode statementContent,
                                     Set<Variable> variables) {

        Statement statementNode = new Statement(statementContent, variables);

        BeanUtils.copyProperties(statementNode, method);
        statementNode.setMethod(method);
        return statementNode;
    }

    public void addVariable(Integer linenum, Variable variable) {
        if (!this.getLineNum().equals(linenum)) {
            return;
        }

        if (null != variables) {
            variables.add(variable);
        } else {
            variables = new HashSet<>();
            variables.add(variable);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Statement statement = (Statement) o;
        return Objects.equals(statementContent, statement.statementContent) &&
                Objects.equals(method, statement.method) &&
                Objects.equals(variables, statement.variables);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), statementContent, method, variables);
    }
}
