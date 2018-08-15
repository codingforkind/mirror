package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Method implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String content;
    private Integer startLineNum;
    private Integer endLineNum;

    Method() {
    }

    Method(String name, String content, Integer startLineNum, Integer endLineNum) {
        this.name = name;
        this.content = content;
        this.startLineNum = startLineNum;
        this.endLineNum = endLineNum;
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
        return Objects.equals(name, method.name) &&
                Objects.equals(content, method.content) &&
                Objects.equals(startLineNum, method.startLineNum) &&
                Objects.equals(endLineNum, method.endLineNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content, startLineNum, endLineNum);
    }
}
