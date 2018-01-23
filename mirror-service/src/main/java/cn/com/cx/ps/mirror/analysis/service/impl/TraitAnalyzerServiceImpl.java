package cn.com.cx.ps.mirror.analysis.service.impl;


import cn.com.cx.ps.mirror.analysis.service.TraitAnalyzerService;
import cn.com.cx.ps.mirror.java.variable.Class;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class TraitAnalyzerServiceImpl implements TraitAnalyzerService {
    @Override
    public Map<String, String> analyzePackages(String prjDir) {
        return null;
    }

    @Override
    public String getPackageName(String javaFilePath) {
        return null;
    }

    @Override
    public Map<String, Set<Class>> analyzeClasses(String prjDir) {
        return null;
    }
}
