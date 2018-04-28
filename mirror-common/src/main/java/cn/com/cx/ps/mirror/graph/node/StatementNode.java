/**
 * 
 */
package cn.com.cx.ps.mirror.graph.node;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import cn.com.cx.ps.mirror.java.variable.Variable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Piggy
 *
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
public class StatementNode extends MethodNode {
	private static final long serialVersionUID = 1L;

	private ASTNode statementContent;

	private Set<Variable> variables = new HashSet<>();
	
	public StatementNode() {
	}

	public StatementNode(String methodName, Integer methodStartLinenum, Integer methodEndLinenum, ASTNode methodContent,
			ASTNode statementContent, Set<Variable> variables) {
		this.statementContent = statementContent;
		this.variables = variables;
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
