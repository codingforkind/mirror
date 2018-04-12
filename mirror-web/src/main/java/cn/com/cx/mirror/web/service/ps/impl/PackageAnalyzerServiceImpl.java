package cn.com.cx.mirror.web.service.ps.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.stereotype.Service;

import cn.com.cx.mirror.web.service.ps.PackageAnalyzerService;
import cn.com.cx.ps.mirror.visitor.PackageVisitor;

@Service
public class PackageAnalyzerServiceImpl implements PackageAnalyzerService {

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
    	PackageVisitor packageVisitor = new PackageVisitor();
        compilationUnit.accept(packageVisitor);
        return packageVisitor.getPackageName();
    }
}
