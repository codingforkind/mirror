package cn.com.cx.ps.mirror.project.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.project.service.ProjectService;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年1月28日
 */
@Component
public class ProjectServiceImpl implements ProjectService {

	@Override
	public boolean classDefinedInProject(Map<String, Set<Class>> prjClasses, String qualifiedClassName) {
		Assert.notNull(prjClasses, "project classes parameter can not be NULL");
		Set<Entry<String, Set<Class>>> entrySet = prjClasses.entrySet();
		Iterator<Entry<String, Set<Class>>> classIterator = entrySet.iterator();
		while (classIterator.hasNext()) {
			Entry<String, Set<Class>> nextClass = classIterator.next();
			Set<Class> clsValSet = nextClass.getValue();
			for (Class clsVal : clsValSet) {
				if (clsVal.getQualifiedName().contains(qualifiedClassName)) {
					// TODO 内部类需要处理
					return true;
				}
			}
		}
		return false;
	}

}
