package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Statement implements Serializable {
    private Integer lineNum;
    private String content;
    private Set<Variable> varsInStat = new TreeSet<>();

    private Method inMethod;

    public Statement(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
