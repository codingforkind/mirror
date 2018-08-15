package cn.com.mirror.project.unit;

import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Variable;
import lombok.Data;
import org.apache.commons.lang3.Validate;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Data
public class Unit implements Serializable {
    /**
     * Unit is represent a project posted by a user.
     */

    private String path; // archive's location
    private Set<String> targets; // target files in the archive, such as project file in a unit.

    private Map<String, CompilationUnit> compilationUnits = new HashMap<>(); // all compilation units in the unit

    private Map<String, String> packages = new HashMap<>();
    private Map<String, Set<Class>> classes = new HashMap<>();
    private Map<String, Set<Method>> methods = new HashMap<>();
    private Map<String, Set<Variable>> variables = new HashMap<>(); // all variables in the unit
    private Map<String, Map<Integer, Set<Variable>>> mappedVars = new HashMap<>(); // all variables in a single code line

    public void addCompilationUnit(String targetPath, CompilationUnit compilationUnit) {
        checkTargetPath(targetPath);
        this.compilationUnits.put(targetPath, compilationUnit);
    }

    public void addClasses(String targetPath, Set<Class> clsSet) {
        checkTargetPath(targetPath);
//        Validate.notEmpty(clsSet, "Target has none classes."); // enum
        this.classes.put(targetPath, clsSet);
    }

    public void addMethods(String targetPath, Set<Method> methods) {
        checkTargetPath(targetPath);
        this.methods.put(targetPath, methods);
    }

    public void addPackages(String targetPath, String packageName) {
        checkTargetPath(targetPath);
        Validate.notEmpty(packageName, "Package mtdName can not be empty.");
        this.packages.put(targetPath, packageName);
    }

    public void addVariables(String targetPath, Set<Variable> variableSet) {
        checkTargetPath(targetPath);
        this.variables.put(targetPath, variableSet);
    }

    public void addMappedVars(String targetPath, Map<Integer, Set<Variable>> variableInFile) {
        checkTargetPath(targetPath);
        this.mappedVars.put(targetPath, variableInFile);
    }

    private void checkTargetPath(String targetPath) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
    }
}