/**
 * 
 */
package cn.com.cx.ps.mirror.graph.node;

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
public class ClassNode extends PackageNode {
	private static final long serialVersionUID = 1L;

	private String className;

}
