package cn.com.cx.mirror.web.service.ps.impl;


import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.com.cx.mirror.web.service.ps.TraitAnalyzerService;
import cn.com.cx.ps.mirror.java.variable.Class;

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
