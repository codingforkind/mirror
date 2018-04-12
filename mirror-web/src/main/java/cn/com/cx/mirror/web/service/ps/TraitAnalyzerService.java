package cn.com.cx.mirror.web.service.ps;

import java.util.Map;
import java.util.Set;

import cn.com.cx.ps.mirror.java.variable.Class;

public interface TraitAnalyzerService {

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

    public Map<String, Set<Class>> analyzeClasses(String prjDir);


}
