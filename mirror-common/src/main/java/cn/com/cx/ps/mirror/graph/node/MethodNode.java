/**
 * 
 */
package cn.com.cx.ps.mirror.graph.node;

import org.eclipse.jdt.core.dom.ASTNode;

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
public class MethodNode extends ClassNode {
	private static final long serialVersionUID = 1L;

	private String methodName;
	private Integer methodStartLinenum;
	private Integer methodEndLinenum;
	private ASTNode methodContent;
	
	public MethodNode() {
	}

	public MethodNode(String methodName, Integer methodStartLinenum, Integer methodEndLinenum, ASTNode methodContent) {
		this.methodName = methodName;
		this.methodStartLinenum = methodStartLinenum;
		this.methodEndLinenum = methodEndLinenum;
		this.methodContent = methodContent;
	}
	
}
