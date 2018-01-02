package cn.com.cx.ps.mirror.service.analyzer.impl;

import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.service.analyzer.PackageAnalyzer;
import cn.com.cx.ps.mirror.tools.visitor.PackageVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class PackageAnalyzerImpl implements PackageAnalyzer {

    @Autowired
    private MirrorProject mirrorProject;

    @Autowired
    private PackageVisitor packageVisitor;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, String> extractPackages(Map<String, CompilationUnit> prjCompUnits) {
        Map<String, String> map = new HashMap<>();
        Set<Map.Entry<String, CompilationUnit>> entries = prjCompUnits.entrySet();
        if (null != entries) {
            Iterator<Map.Entry<String, CompilationUnit>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, CompilationUnit> next = iterator.next();
                map.put(next.getKey(), parserPackage(next.getValue()));
            }
        }
        return map;
    }

    @Override
    public String parserPackage(CompilationUnit compilationUnit) {
        compilationUnit.accept(packageVisitor);
        return packageVisitor.getPackageName();
    }
}
