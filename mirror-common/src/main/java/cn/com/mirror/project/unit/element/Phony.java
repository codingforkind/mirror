package cn.com.mirror.project.unit.element;

import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * @author piggy
 * @description
 * @date 18-8-17
 */
public class Phony extends Method {

    public static final String PHONY = "PHONY METHOD";
    
    public Phony(String targetPath,
                 Integer startLineNum,
                 Integer endLineNum,
                 String content,
                 String packageName,
                 String name,
                 MethodDeclaration methodDeclaration,
                 Class inClass) {

        super(targetPath, startLineNum, endLineNum, content, packageName, name, methodDeclaration, inClass);
    }



}
