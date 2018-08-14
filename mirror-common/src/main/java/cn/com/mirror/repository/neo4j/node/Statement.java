/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.utils.BeanUtils;
import cn.com.mirror.project.unit.variable.Variable;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Piggy
 * @description
 */
@Getter
@Setter
public class Statement extends Method {
    private static final long serialVersionUID = 1L;

    private ASTNode statementContent;

    private Set<Variable> variables = new HashSet<>();

    Statement() {
    }

    Statement(String methodName,
              Integer methodStartLineNum,
              Integer methodEndLineNum,
              ASTNode methodContent,
              ASTNode statementContent,
              Set<Variable> variables) {

        super(methodName, methodStartLineNum, methodEndLineNum, methodContent);
        this.statementContent = statementContent;
        this.variables = variables;
    }

    public static Statement instance(Method method) {
        Statement statementNode = (Statement) JSON.parse(JSON.toJSONString(method));
        return statementNode;
    }

    public static Statement instance(Method method,
                                     String methodName,
                                     Integer methodStartLinenum,
                                     Integer methodEndLinenum,
                                     ASTNode methodContent,
                                     ASTNode statementContent,
                                     Set<Variable> variables) {

        Statement statementNode = new Statement(methodName, methodStartLinenum, methodEndLinenum, methodContent,
                statementContent, variables);
        BeanUtils.copyProperties(statementNode, method);
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

}
