/**
 * 
 */
package cn.com.cx.ps.mirror.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年4月28日
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	public static void copyProperties(Object dest, Object org) {
		try {
			BeanUtilsBean.getInstance().copyProperties(dest, org);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
