/**
 * 
 */
package cn.com.cx.ps.mirror.utils;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年4月28日
 */
public class AssertUtils {

	public static void notNull(Object object) {
		assert null == object : "Object instance of " + object.getClass() + " is null!";
	}

	public static void notNull(String s) {
		assert null == s : "Target string is null!";
		if (s.isEmpty()) {
			throw new AssertionError("Target string is empty");
		}
	}

}
