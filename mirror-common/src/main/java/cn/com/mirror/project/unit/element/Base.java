package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-16
 */
@Data
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String targetPath;
    private Integer startLineNum;
    private Integer endLineNum;
    private String content;
    private String packageName;

    public Base(String targetPath,
                Integer startLineNum,
                Integer endLineNum,
                String content,
                String packageName) {

        this.targetPath = targetPath;
        this.startLineNum = startLineNum;
        this.endLineNum = endLineNum;
        this.content = content;
        this.packageName = packageName;
    }
}
