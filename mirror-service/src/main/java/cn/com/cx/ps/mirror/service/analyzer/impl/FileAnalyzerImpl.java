package cn.com.cx.ps.mirror.service.analyzer.impl;

import cn.com.cx.ps.mirror.configuration.MirrorProject;
import cn.com.cx.ps.mirror.service.analyzer.FileAnalyzer;
import cn.com.cx.ps.mirror.utils.AstUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class FileAnalyzerImpl implements FileAnalyzer {

//    @Autowired
    private MirrorProject mirrorProject;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, CompilationUnit> extractCompilationUnits(Set<String> javaFiles) {
        Map<String, CompilationUnit> map = new HashMap<>();
        for (String javaFile : javaFiles) {
            CompilationUnit unit = parserCompilationUnit(javaFile);
            map.put(javaFile, unit);
        }
        return map;
    }

    @Override
    public CompilationUnit parserCompilationUnit(String javaFile) {
        return AstUtils.getCompUnitResolveBinding(javaFile);
    }


}
