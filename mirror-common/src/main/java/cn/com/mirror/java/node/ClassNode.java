/**
 * 
 */
package cn.com.mirror.java.node;

import cn.com.mirror.utils.BeanUtils;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;

/**
 * @author Piggy
 *
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
public class ClassNode extends Node {
	private static final long serialVersionUID = 1L;

	private String packageName;
	private String className;
	private Integer classEndLinenum;
	private ASTNode classContent;

	ClassNode() {
	}

	ClassNode(String packageName, String className, Integer classEndLinenum, ASTNode classContent) {
		this.packageName = packageName;
		this.className = className;
		this.classEndLinenum = classEndLinenum;
		this.classContent = classContent;
	}

	public static ClassNode instance(Node node) {
		ClassNode classNode = (ClassNode) JSON.parse(JSON.toJSONString(node));
		return classNode;
	}

	public static ClassNode instance(Node node, String packageName, String className, Integer classEndLinenum,
			ASTNode classContent) {
		ClassNode classNode = new ClassNode(packageName, className, classEndLinenum, classContent);
		BeanUtils.copyProperties(classNode, node);
		return classNode;
	}
}
