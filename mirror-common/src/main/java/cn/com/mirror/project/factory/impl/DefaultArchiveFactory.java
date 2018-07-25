package cn.com.mirror.project.factory.impl;

import cn.com.mirror.project.Archive;
import cn.com.mirror.project.factory.ArchiveFactory;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
public class DefaultArchiveFactory implements ArchiveFactory {

    @Override
    public Archive archive() {
        return new Archive();
    }
}
