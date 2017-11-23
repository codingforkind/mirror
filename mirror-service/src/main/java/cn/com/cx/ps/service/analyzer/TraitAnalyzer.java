package cn.com.cx.ps.service.analyzer;

import cn.com.cx.ps.variable.CustomizedClass;

import java.util.Map;
import java.util.Set;

public interface TraitAnalyzer {

    /**
     * @param prjDir absolute path for the project
     * @return <p>key: java file path</p>
     * <p>value: full package name</p>
     */
    public Map<String, String> analyzePackages(String prjDir);

    /**
     * @param javaFilePath absolute path for the java file
     * @return <p>full package name for the java file</p>
     */
    public String getPackageName(String javaFilePath);

    public Map<String, Set<CustomizedClass>> analyzeClasses(String prjDir);


}
