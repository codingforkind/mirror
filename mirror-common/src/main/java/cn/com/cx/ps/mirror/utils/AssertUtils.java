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

	public static void notNull(Object object, String message) {
		assert null == object : message;
	}

	public static void notNull(String s, String message) {
		assert null == s : message;
		if (s.isEmpty()) {
			throw new AssertionError("Target string is empty");
		}
	}

}
