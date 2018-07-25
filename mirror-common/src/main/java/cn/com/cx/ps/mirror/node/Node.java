/**
 * 
 */
package cn.com.cx.ps.mirror.node;

import java.io.Serializable;

import cn.com.cx.ps.mirror.constant.NodeTypeEnum;
import lombok.Data;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年4月27日
 */
@Data
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nodeId;
	private NodeTypeEnum nodeTypeEnum;
	/**
	 * also is the startLine of @see ClassNode and @see MethodNode
	 */
	private Integer linenum;
	private String javaFilePath;

	public Node() {
	}

	public Node(String nodeId, NodeTypeEnum nodeTypeEnum, int linenum, String javaFilePath) {
		this.nodeId = nodeId;
		this.nodeTypeEnum = nodeTypeEnum;
		this.linenum = linenum;
		this.javaFilePath = javaFilePath;
	}

}
