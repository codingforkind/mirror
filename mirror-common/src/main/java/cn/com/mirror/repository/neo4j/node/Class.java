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
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeId;
    private NodeTypeEnum nodeTypeEnum;
    private Integer lineNum;
    private String javaFilePath;


    private String packageName;
    private String className;
    private Integer classEndLineNum;
    private ASTNode classContent;

    Class() {
    }

    Class(String packageName,
          String className,
          Integer classEndLineNum,
          ASTNode classContent, String nodeId,
          NodeTypeEnum nodeTypeEnum,
          int lineNum,
          String javaFilePath) {

        this.nodeId = nodeId;
        this.nodeTypeEnum = nodeTypeEnum;
        this.lineNum = lineNum;
        this.javaFilePath = javaFilePath;
        this.packageName = packageName;
        this.className = className;
        this.classEndLineNum = classEndLineNum;
        this.classContent = classContent;
    }

    public static Class instance(String packageName,
                                 String className,
                                 Integer classEndLineNum,
                                 ASTNode classContent,
                                 String nodeId,
                                 NodeTypeEnum nodeTypeEnum,
                                 int lineNum,
                                 String javaFilePath) {

        Class classNode = new Class(packageName,
                className,
                classEndLineNum,
                classContent,
                nodeId,
                nodeTypeEnum,
                lineNum,
                javaFilePath);
        return classNode;
    }
}
