package cn.com.cx.ps.mirror.analysis.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.analysis.service.PackageAnalyzerService;
import cn.com.cx.ps.mirror.common.visitor.PackageVisitor;

@Service
public class PackageAnalyzerServiceImpl implements PackageAnalyzerService {

    @Autowired
    private PackageVisitor packageVisitor;

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
