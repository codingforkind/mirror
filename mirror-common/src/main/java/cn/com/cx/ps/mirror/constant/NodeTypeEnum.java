/**
 * 
 */
package cn.com.cx.ps.mirror.constant;

import lombok.Getter;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年4月27日
 */
@Getter
public enum NodeTypeEnum {
	PACKAGE("PACKAGE"), 
	CLASS("CLASS"), 
	METHOD("METHOD"), 
	STATEMENT("STATEMENT");

	private NodeTypeEnum(String key) {
		this.key = key;
	}

	private String key;

	public static NodeTypeEnum getNodeTypeEnum(String key) {
		for (NodeTypeEnum nodeTypeEnum : NodeTypeEnum.values()) {
			if (nodeTypeEnum.getKey().equals(key)) {
				return nodeTypeEnum;
			}
		}
		throw new RuntimeException("No node type match!");
	}

}
