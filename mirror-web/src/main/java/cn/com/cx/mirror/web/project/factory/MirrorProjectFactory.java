package cn.com.cx.mirror.web.project.factory;

import cn.com.cx.mirror.web.project.MirrorProject;

/**
 * @author Piggy
 *
 * @description 
 * @date 2018年1月28日
 */
public interface MirrorProjectFactory {

	/**
	 * Generate a mirror project, initialize the mirror project and extract all of the java files 
	 * and initialize it.
	 * @return
	 */
	public MirrorProject mirrorProject();

}
