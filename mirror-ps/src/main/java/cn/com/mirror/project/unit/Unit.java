package cn.com.mirror.project.unit;

import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Root;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;
import org.apache.commons.lang3.Validate;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
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
    private Set<String> targets; // target files in the archive, such as project file in a project.

    private Map<String, CompilationUnit> compilationUnits = new HashMap<>(); // all compilation units in the project

    private Map<String, String> packages = new HashMap<>();
    private Set<Root> roots = new HashSet<>();
    private Map<String, Set<Class>> classes = new HashMap<>();
    private Map<String, Set<Method>> methods = new HashMap<>();
    private Map<String, Map<Integer, Statement>> statements = new HashMap<>(); // all variables in a single code line
    private Map<String, Set<Variable>> variables = new HashMap<>(); // all variables in the project

    public void addCompilationUnit(String targetPath, CompilationUnit compilationUnit) {
        checkTargetPath(targetPath);
        this.compilationUnits.put(targetPath, compilationUnit);
    }

    public void addRoot(Root root) {
        this.roots.add(root);
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
        Validate.notEmpty(packageName, "Package name can not be empty.");
        this.packages.put(targetPath, packageName);
    }

    public void addVariables(String targetPath, Set<Variable> variableSet) {
        checkTargetPath(targetPath);
        this.variables.put(targetPath, variableSet);
    }

    public void addMappedVars(String targetPath, Map<Integer, Statement> statements) {
        checkTargetPath(targetPath);
        this.statements.put(targetPath, statements);
    }

    private void checkTargetPath(String targetPath) {
        Validate.notEmpty(targetPath, "Target's path can not be empty.");
    }

    public Root getRoot(String pkgName) {
        assert null == pkgName : "Package name can not be null.";
        assert pkgName.isEmpty() : "Package name can not be empty";

        for (Root root : this.roots) {
            if (pkgName.equals(root.getPackageName())) {
                return root;
            }
        }
        throw new UnitException("No root found in this project.");
    }
}