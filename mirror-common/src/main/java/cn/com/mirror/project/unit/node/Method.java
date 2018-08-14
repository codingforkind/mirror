/**
 * 
 */
package cn.com.mirror.project.unit.node;

import cn.com.mirror.utils.BeanUtils;
import org.eclipse.jdt.core.dom.ASTNode;

import com.alibaba.fastjson.JSON;

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
public class Method extends Class {
	private static final long serialVersionUID = 1L;

	private String methodName;
	private Integer methodEndLinenum;
	private ASTNode methodContent;

	Method() {
	}

	Method(String methodName, Integer methodEndLinenum, ASTNode methodContent) {
		this.methodName = methodName;
		this.methodEndLinenum = methodEndLinenum;
		this.methodContent = methodContent;
	}

	public static Method instance(Class classNode) {
		Method method = (Method) JSON.parse(JSON.toJSONString(classNode));
		return method;
	}

	public static Method instance(Class classNode, String methodName, Integer methodStartLinenum,
								  Integer methodEndLinenum, ASTNode methodContent) {
		Method method = new Method(methodName, methodEndLinenum, methodContent);
		BeanUtils.copyProperties(method, classNode);
		return method;
	}
}
