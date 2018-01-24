package cn.com.cx.ps.mirror.project.factory;

import cn.com.cx.ps.mirror.project.MirrorProject;

public interface MirrorProjectFactory {

	/**
	 * Generate a mirror project, initialize the mirror project and extract all of the java files.
	 * @return
	 */
	public MirrorProject generateMirrorProject();

}
