package cn.com.mirror.project.unit;

import cn.com.mirror.project.unit.variable.Class;
import cn.com.mirror.project.unit.variable.Variable;
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
     *
     */

    private String path; // archive's location
    private Set<String> targets; // target files in the archive, such as project file in a project project.

    private Map<String, CompilationUnit> compilationUnits = new HashMap<>(); // all compilation units in this project archive

    private Map<String, String> packages = new HashMap<>(); // all packages in this project archive
    private Map<String, Set<Class>> classes = new HashMap<>(); // all classes in this project archive
    private Map<String, Set<Variable>> variables = new HashMap<>(); // all variables in this project archive
    private Map<String, Map<Integer, Set<Variable>>> mappedVars = new HashMap<>(); // all variables in a single code line

    public void addCompilationUnit(String targetPath, CompilationUnit compilationUnit) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
        compilationUnits.put(targetPath, compilationUnit);
    }

    public void addClasses(String targetPath, Set<Class> clsSet) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
//        Validate.notEmpty(clsSet, "Target has none classes."); // enum
        classes.put(targetPath, clsSet);
    }

    public void addPackages(String targetPath, String packageName) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
        Validate.notEmpty(packageName, "Package name can not be empty.");
        packages.put(targetPath, packageName);
    }

    public void addVariables(String targetPath, Set<Variable> variableSet) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
        variables.put(targetPath, variableSet);
    }

    public void addMappedVars(String targetPath, Map<Integer, Set<Variable>> variableInFile) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
        mappedVars.put(targetPath, variableInFile);
    }
}