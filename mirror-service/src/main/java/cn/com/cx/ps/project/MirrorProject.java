package cn.com.cx.ps.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.cx.ps.variable.CustomizedClass;
import cn.com.cx.ps.exceptions.ProjectException;
import cn.com.cx.ps.utils.AstUtils;
import cn.com.cx.ps.visitor.VariableVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.visitor.PackageVisitor;

public class MirrorProject {
    private static Logger logger = LoggerFactory.getLogger(Project.class);
    private File prjPath;
    private Set<String> prjJavaFiles = new HashSet<>();
    private Set<String> packages = new HashSet<>();
    private int fileCount;

    private Map<String, ClassFile> javaFiles = new HashMap<>();


    // just for test purpose
    public MirrorProject(String filePath) {
        PackageVisitor packageVisitor = null;
        ClassDeclarationVisitor classDeclarationVisitor = null;
        VariableVisitor variableVisitor = null;
        // get all statement lines for each file
        ClassFile classFile = new ClassFile(filePath);
        classFile.setStatements(this.getAllStatements(filePath));

        // package
        classFile.setCompilationUnit(AstUtils.getCompUnitResolveBinding(filePath));
        packageVisitor = new PackageVisitor();
        classFile.getCompilationUnit().accept(packageVisitor);
        this.packages.add(packageVisitor.getPackageName());

        // class
        classDeclarationVisitor = new ClassDeclarationVisitor();
        classFile.getCompilationUnit().accept(classDeclarationVisitor);
        classFile.setClassesInFile(classDeclarationVisitor.getCustomizedClasses());

        // variables
        variableVisitor = new VariableVisitor(filePath, this);
        classFile.getCompilationUnit().accept(variableVisitor);
        classFile.setVariablesInFile(variableVisitor.getVariables());

        this.javaFiles.put(filePath, classFile);
    }

    public MirrorProject(File prjDir) throws ProjectException {
        this.prjPath = prjDir;
        if (!prjDir.isDirectory()) {
            throw new ProjectException(prjDir.getAbsolutePath() + " is not a dir.");
        }
        // get all files in this project ant the count of the files(".java")
        getJavaFiles(prjDir);

        PackageVisitor packageVisitor = null;
        ClassDeclarationVisitor classDeclarationVisitor = null;
        VariableVisitor variableVisitor = null;
        for (String tmpPath : prjJavaFiles) {
            // get all statement lines for each file
            ClassFile classFile = new ClassFile(tmpPath);
            classFile.setStatements(this.getAllStatements(tmpPath));

            // package
            classFile.setCompilationUnit(AstUtils.getCompUnitResolveBinding(tmpPath));
            packageVisitor = new PackageVisitor();
            classFile.getCompilationUnit().accept(packageVisitor);
            this.packages.add(packageVisitor.getPackageName());

            // class
            classDeclarationVisitor = new ClassDeclarationVisitor();
            classFile.getCompilationUnit().accept(classDeclarationVisitor);
            classFile.setClassesInFile(classDeclarationVisitor.getCustomizedClasses());

            // variables
            variableVisitor = new VariableVisitor(tmpPath, this);
            classFile.getCompilationUnit().accept(variableVisitor);
            classFile.setVariablesInFile(variableVisitor.getVariables());

            this.javaFiles.put(tmpPath, classFile);
        }

    }

    private void getJavaFiles(File dir) {
        File[] fs = dir.listFiles();
        String tmpPath = null;
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getAbsolutePath().endsWith(".java")) {
                tmpPath = fs[i].getAbsolutePath();
                this.fileCount += 1;
                prjJavaFiles.add(tmpPath);
            }
            if (fs[i].isDirectory()) {
                getJavaFiles(fs[i]);
            } // end if
        } // end for
    }

    private List<String> getAllStatements(String path) {
        ArrayList<String> lines = new ArrayList<>();
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
        return lines;
    }

    public File getPrjPath() {
        return prjPath;
    }

    public int getFileCount() {
        return fileCount;
    }

    public Map<String, ClassFile> getJavaFiles() {
        return javaFiles;
    }

    public boolean classInProject(String qualifiedClassName) {
        for (ClassFile classFile : this.javaFiles.values()) {
            for (CustomizedClass customizedClass : classFile.getClassesInFile()) {
                if (customizedClass.getQualifiedName().contains(qualifiedClassName)) return true;
                continue;
            }
        }
        return false;
    }

    public void setPrjPath(File prjPath) {
        this.prjPath = prjPath;
    }

    public Set<String> getPrjJavaFiles() {
        return prjJavaFiles;
    }

    public void setPrjJavaFiles(Set<String> prjJavaFiles) {
        this.prjJavaFiles = prjJavaFiles;
    }

    public Set<String> getPackages() {
        return packages;
    }

    public void setPackages(Set<String> packages) {
        this.packages = packages;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public void setJavaFiles(Map<String, ClassFile> javaFiles) {
        this.javaFiles = javaFiles;
    }
}
