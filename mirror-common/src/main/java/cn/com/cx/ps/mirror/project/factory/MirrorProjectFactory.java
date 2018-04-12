package cn.com.cx.ps.mirror.project.factory;

import cn.com.cx.ps.mirror.project.MirrorProject;

/**
 * @author Piggy
 *
 * @description 
 * @date 2018年1月28日
 */
public interface MirrorProjectFactory {

	/**
	 * Mirror project initialization
	 * @param mirrorProject
	 * @return
	 */
	public MirrorProject initMirrorProject(MirrorProject mirrorProject);

	/**
	 * Generate a mirror project, initialize the mirror project and extract all of the java files 
	 * and initialize it.
	 * @return
	 */
	public MirrorProject mirrorProject();

}
