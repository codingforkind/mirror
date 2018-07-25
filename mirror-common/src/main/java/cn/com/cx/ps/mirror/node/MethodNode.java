/**
 * 
 */
package cn.com.cx.ps.mirror.node;

import org.eclipse.jdt.core.dom.ASTNode;

import com.alibaba.fastjson.JSON;

import cn.com.cx.ps.mirror.utils.BeanUtils;
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
	private Integer methodEndLinenum;
	private ASTNode methodContent;

	MethodNode() {
	}

	MethodNode(String methodName, Integer methodEndLinenum, ASTNode methodContent) {
		this.methodName = methodName;
		this.methodEndLinenum = methodEndLinenum;
		this.methodContent = methodContent;
	}

	public static MethodNode instance(ClassNode classNode) {
		MethodNode methodNode = (MethodNode) JSON.parse(JSON.toJSONString(classNode));
		return methodNode;
	}

	public static MethodNode instance(ClassNode classNode, String methodName, Integer methodStartLinenum,
			Integer methodEndLinenum, ASTNode methodContent) {
		MethodNode methodNode = new MethodNode(methodName, methodEndLinenum, methodContent);
		BeanUtils.copyProperties(methodNode, classNode);
		return methodNode;
	}
}
