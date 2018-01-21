package cn.com.cx.ps.mirror.service.analyzer.impl;

import cn.com.cx.ps.mirror.configuration.MirrorProject;
import cn.com.cx.ps.mirror.service.analyzer.ClassAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassAnalyzerImpl implements ClassAnalyzer {

//    @Autowired
    private MirrorProject mirrorProject;

    private Logger log = LoggerFactory.getLogger(getClass());

}
