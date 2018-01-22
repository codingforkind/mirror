package cn.com.cx.ps.mirror.service.analyzer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.cx.ps.mirror.service.analyzer.FileAnalyzerService;
import cn.com.cx.ps.mirror.utils.AstUtils;

@Service
public class FileAnalyzerServiceImpl implements FileAnalyzerService {

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

	@Override
	public List<String> extractLineNums(String filePath) {
//		TODO 待完善
		return AstUtils.listCodeLines(filePath);
	}
    
    


}
