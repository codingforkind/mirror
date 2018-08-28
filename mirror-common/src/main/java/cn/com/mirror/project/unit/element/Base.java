package cn.com.mirror.project.unit.element;

import cn.com.mirror.constant.ElementTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-16
 */
@Data
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    private String targetPath;
    private Integer startLineNum;
    private Integer endLineNum;
    private String content;
    private String packageName;
    private ElementTypeEnum elementTypeEnum;

    public Base(String targetPath,
                Integer startLineNum,
                Integer endLineNum,
                String content,
                String packageName,
                ElementTypeEnum elementTypeEnum) {

        this.targetPath = targetPath;
        this.startLineNum = startLineNum;
        this.endLineNum = endLineNum;
        this.content = content;
        this.packageName = packageName;
        this.elementTypeEnum = elementTypeEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(targetPath, base.targetPath) &&
                Objects.equals(startLineNum, base.startLineNum) &&
                Objects.equals(endLineNum, base.endLineNum) &&
                Objects.equals(content, base.content) &&
                Objects.equals(packageName, base.packageName) &&
                elementTypeEnum == base.elementTypeEnum;
    }

    @Override
    public int hashCode() {

        return Objects.hash(targetPath, startLineNum, endLineNum, content, packageName, elementTypeEnum);
    }
}
