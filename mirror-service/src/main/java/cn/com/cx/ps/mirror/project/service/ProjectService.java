package cn.com.cx.ps.mirror.project.service;

import java.util.Map;
import java.util.Set;

import cn.com.cx.ps.mirror.java.variable.Class;

public interface ProjectService {

	/**
	 * check the class is defined in the project
	 * @param qualifiedClassName
	 * @return
	 */
	public boolean classDefinedInProject(Map<String, Set<Class>> prjClasses, String qualifiedClassName);
}
