package cn.com.cx.ps.project;

import cn.com.cx.ps.variable.CustomizedClass;
import cn.com.cx.ps.variable.Variable;
import cn.com.cx.ps.utils.AstUtils;
import cn.com.cx.ps.utils.AstUtils;
import cn.com.cx.ps.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.visitor.PackageVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

@Deprecated
public class Project {
    private static Logger logger = LoggerFactory.getLogger(Project.class);
    private String prjPath;
    private Set<String> prjJavaFiles = new HashSet<>();
    private Set<String> packages = new HashSet<>();
    
    
    private HashMap<String, ArrayList<String>> statements = new HashMap<>();
    
    private HashMap<String, CompilationUnit> compilationUnits = new HashMap<>();

    private Set<CustomizedClass> definedTypes = new HashSet<>();
    private Map<String, Set<Variable>> prjVariables = new HashMap<>();

    public Project(String file) {
        this.compilationUnits.put(file, AstUtils.getCompUnitResolveBinding(file));
        PackageVisitor visitor = new PackageVisitor();
        this.compilationUnits.get(file).accept(visitor);
        addPackage(visitor.getPackageName());
        initClasses(file);
        initPackages(file);
        initVariables();
    }

    public Project(String path, boolean isProject) {
        logger.info("Accessing all file in this project which is in " + path);
        this.prjPath = path;
        logger.info("Init files in this project: {}", path);
        initJavaFiles(path);
        logger.info("Building CompilationUnits for the project in " + path);
        this.initCompilationUnits(path);
    }

    private void initPackages(String file) {
        if(!prjJavaFiles.isEmpty()) {
            for (String tmJavaFile : prjJavaFiles) {
                PackageVisitor visitor = new PackageVisitor();
                this.compilationUnits.get(tmJavaFile).accept(visitor);
                addPackage(visitor.getPackageName());
            }
        }else{
            PackageVisitor visitor = new PackageVisitor();
            AstUtils.getCompUnitResolveBinding(file).accept(visitor);
            addPackage(visitor.getPackageName());
        }
    }

    private void initVariables() {

    }

    private void initClasses(String file) {
        if(!prjJavaFiles.isEmpty()){
            for (String tmJavaFile : prjJavaFiles) {
                ClassDeclarationVisitor visitor = new ClassDeclarationVisitor();
                this.compilationUnits.get(tmJavaFile).accept(visitor);
                this.definedTypes.addAll(visitor.getCustomizedClasses());
            }
        }else{
            ClassDeclarationVisitor visitor = new ClassDeclarationVisitor();
            AstUtils.getCompUnitResolveBinding(file).accept(visitor);
            this.definedTypes.addAll(visitor.getCustomizedClasses());
        }
    }

    private final void initJavaFiles(String prjPath) {
        getJavaFiles(new File(prjPath));
        for (String tmpPath : this.getPrjJavaFiles()) {
            //get all statement lines for each file
            this.getAllStatements(tmpPath);
        }
    }

    private final boolean initCompilationUnits(String prjPath) {
        if (this.prjJavaFiles.isEmpty()) {
            logger.error("Please read and cache the files in this project: {}", prjPath);
            return false;
        }

        for (String tmpFile : this.getPrjJavaFiles()) {
            CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(tmpFile);
            this.compilationUnits.put(tmpFile, compilationUnit);
        }
        return true;
    }

    private void getAllStatements(String path) {
        ArrayList<String> lines = new ArrayList<>();
        @SuppressWarnings("resource")
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(new File(path), "r");
        } catch (FileNotFoundException e) {
            logger.error("File: " + path + " is not found!");
            e.printStackTrace();
        }
        try {
            if (accessFile != null) {
                String line = accessFile.readLine();
                lines.add(line);
                while (line != null) {
                    line = accessFile.readLine();
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            logger.error("Read file: " + path);
            e.printStackTrace();
        }
        this.statements.put(path, lines);
    }


    private void getJavaFiles(File dir) {
        File[] fs = dir.listFiles();
        String tmpPath = null;
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getAbsolutePath().endsWith(".java")) {
                tmpPath = fs[i].getAbsolutePath();
                prjJavaFiles.add(tmpPath);
            }
            if (fs[i].isDirectory()) {
                getJavaFiles(fs[i]);
            } // end if
        } // end for
    }

    public Set<String> getPrjJavaFiles() {
        return prjJavaFiles;
    }

    public void setPrjJavaFiles(Set<String> prjJavaFiles) {
        this.prjJavaFiles = prjJavaFiles;
    }

    public String getPrjPath() {
        return prjPath;
    }

    public void setPrjPath(String prjPath) {
        this.prjPath = prjPath;
    }

    public HashMap<String, ArrayList<String>> getStatements() {
        return statements;
    }

    public CompilationUnit getCompilationUnit(String file) {
        if (this.compilationUnits.containsKey(file)) return this.compilationUnits.get(file);
        if (file.contains(this.prjPath)) {
//            if file in this project then cache it into the map
            CompilationUnit unit = AstUtils.getCompUnitResolveBinding(file);
            this.compilationUnits.put(file, unit);
        }

        return AstUtils.getCompUnitResolveBinding(file);
    }

    public Set<CustomizedClass> getDefinedTypes() {
        return definedTypes;
    }

    public void addDefinedType(CustomizedClass defInterface) {
        this.definedTypes.add(defInterface);
    }

    public Map<String, Set<Variable>> getPrjVariables() {
        return prjVariables;
    }

    public void addPrjVariable(String javaFile, Variable variable) {
        if (this.prjVariables.containsKey(javaFile)) {
            this.prjVariables.get(javaFile).add(variable);
        } else {
            Set<Variable> tmSet = new HashSet<>();
            tmSet.add(variable);
            this.prjVariables.put(javaFile, tmSet);
        }
    }

    public Set<String> getPackages() {
        return packages;
    }

    public void addPackage(String packageName) {
        this.packages.add(packageName);
    }

    public boolean packageInProject(String packgeName) {
        return this.packages.contains(packgeName);
    }

    public boolean classInProject(String qualifiedClassName) {
        for (CustomizedClass customizedClass : this.definedTypes) {
            if (customizedClass.getQualifiedName().contains(qualifiedClassName)) return true;
            continue;
        }
        return false;
    }
}
