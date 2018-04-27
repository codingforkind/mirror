/**
 * 
 */
package cn.com.cx.ps.mirror.graph.node;

import java.io.Serializable;

import cn.com.cx.ps.mirror.constant.NodeTypeEnum;
import lombok.Data;

/**
 * @author Piggy
 *
 * @description
 * @since 2018年4月19日
 */
@Data
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nodeId;
	private NodeTypeEnum nodeTypeEnum;
	private Integer linenum;
	private String javaFile;
	private String content;
}
