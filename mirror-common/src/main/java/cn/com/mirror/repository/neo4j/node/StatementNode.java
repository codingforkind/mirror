/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.project.unit.element.Variable;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Piggy
 * @description
 */
@Getter
@Setter
@NodeEntity(label = "a line of code in target file")
public class StatementNode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Property(name = "statements content")
    private String statementContent;

    @Property(name = "current statements belongs to this method")
    @Relationship(type = EdgeType.EDGE_TYPE.STATEMENT_TO_METHOD_CTRL_EDGE)
    private MethodNode method;

    private Set<Variable> variables = new HashSet<>();

    StatementNode(ASTNode statementContent,
                  Set<Variable> variables) {

        this.statementContent = statementContent.toString();
        this.variables = variables;
    }

    public static StatementNode instance(Statement statement) {

        Method mtd = statement.getInMethod();
        Class cls = mtd.getInClass();

//        TODO xyz isolated all nodes
        return null;
    }

}
