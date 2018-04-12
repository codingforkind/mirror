/**
 * 
 */
package cn.com.cx.mirror.web.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;

import cn.com.cx.ps.mirror.java.variable.Variable;
import lombok.Data;

/**
 * @author Piggy basic node in neo4j graph
 * @description
 * @since 2018年3月20日
 */
@Data
@NodeEntity
public class BaseNode implements Serializable {
	private static final long serialVersionUID = -5897109342610042182L;

	private Integer linenum;
	private Set<Variable> variables;

	public BaseNode() {
	}

	public BaseNode(Integer linenum) {
		this.linenum = linenum;
	}

	public void addVariable(Integer linenum, Variable variable) {
		if (!this.linenum.equals(linenum)) {
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
