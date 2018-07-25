/**
 * 
 */
package cn.com.cx.ps.mirror.node;

import cn.com.cx.ps.mirror.variable.Variable;
import cn.com.cx.ps.mirror.utils.BeanUtils;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Piggy
 *
 * @description
 */
@Getter
@Setter
public class StatementNode extends MethodNode {
	private static final long serialVersionUID = 1L;

	private ASTNode statementContent;

	private Set<Variable> variables = new HashSet<>();

	StatementNode() {
	}

	StatementNode(String methodName, Integer methodStartLinenum, Integer methodEndLinenum, ASTNode methodContent,
			ASTNode statementContent, Set<Variable> variables) {
		this.statementContent = statementContent;
		this.variables = variables;
	}

	public static StatementNode instance(MethodNode methodNode) {
		StatementNode statementNode = (StatementNode) JSON.parse(JSON.toJSONString(methodNode));
		return statementNode;
	}

	public static StatementNode instance(MethodNode methodNode, String methodName, Integer methodStartLinenum,
			Integer methodEndLinenum, ASTNode methodContent, ASTNode statementContent, Set<Variable> variables) {

		StatementNode statementNode = new StatementNode(methodName, methodStartLinenum, methodEndLinenum, methodContent,
				statementContent, variables);
		BeanUtils.copyProperties(statementNode, methodNode);
		return statementNode;
	}

	public void addVariable(Integer linenum, Variable variable) {
		if (!this.getLinenum().equals(linenum)) {
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
