/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.NodeTypeEnum;
import lombok.Data;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.Serializable;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Data
public class Class extends Node {
    private static final long serialVersionUID = 1L;

    private String packageName;
    private String className;
    private Integer classStartLineNum;
    private Integer classEndLineNum;
    private String classContent;

    Class() {
    }

    Class(String packageName,
          String className,
          Integer classStartLineNum,
          Integer classEndLineNum,
          ASTNode classContent) {

        this.packageName = packageName;
        this.className = className;
        this.classStartLineNum = classStartLineNum;
        this.classEndLineNum = classEndLineNum;
        this.classContent = classContent.toString();
    }

    public static Class instance(String packageName,
                                 String className,
                                 Integer classStartLineNum,
                                 Integer classEndLineNum,
                                 ASTNode classContent) {

        Class classNode = new Class(packageName, className, classStartLineNum, classEndLineNum, classContent);
        return classNode;
    }
}
