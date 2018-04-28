/**
 * 
 */
package cn.com.cx.ps.mirror.graph.node;

import org.eclipse.jdt.core.dom.ASTNode;

import com.alibaba.fastjson.JSON;

import cn.com.cx.ps.mirror.utils.AssertUtils;
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
public class ClassNode extends Node {
	private static final long serialVersionUID = 1L;

	private String packageName;
	private String className;
	private Integer classStartLinenum;
	private Integer classEndLinenum;
	private ASTNode classContent;

	public ClassNode() {
	}

	private ClassNode(String packageName, String className, Integer classStartLinenum, Integer classEndLinenum,
			ASTNode classContent) {
		this.packageName = packageName;
		this.className = className;
		this.classStartLinenum = classStartLinenum;
		this.classEndLinenum = classEndLinenum;
		this.classContent = classContent;
	}

	public static ClassNode instance(Node node) {
		AssertUtils.notNull(node, "Node must not null!");
		ClassNode classNode = (ClassNode) JSON.parse(JSON.toJSONString(node));
		return classNode;
	}

	public static ClassNode instance(Node node, String packageName, String className, Integer classStartLinenum,
			Integer classEndLinenum, ASTNode classContent) {
		AssertUtils.notNull(node, "Node must not null!");

		ClassNode classNode = new ClassNode(packageName, className, classStartLinenum, classEndLinenum, classContent);
		// (ClassNode) JSON.parse(JSON.toJSONString(node));
		
		
		return classNode;
	}
}
