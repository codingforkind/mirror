package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Method extends Class {
    private static final long serialVersionUID = 1L;

    private String mtdName;
    private String methodContent;
    private Integer mtdStartLineNum;
    private Integer mtdEndLineNum;

    Method() {
    }

    Method(String name, String content, Integer startLineNum, Integer endLineNum) {
        this.mtdName = name;
        this.methodContent = content;
        this.mtdStartLineNum = startLineNum;
        this.mtdEndLineNum = endLineNum;
    }

    public final static Method instance(String name,
                                        String content,
                                        Integer startLineNum,
                                        Integer endLineNum) {

        return new Method(name, content, startLineNum, endLineNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;
        return Objects.equals(mtdName, method.mtdName) &&
                Objects.equals(methodContent, method.methodContent) &&
                Objects.equals(mtdStartLineNum, method.mtdStartLineNum) &&
                Objects.equals(mtdEndLineNum, method.mtdEndLineNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mtdName, methodContent, mtdStartLineNum, mtdEndLineNum);
    }
}
