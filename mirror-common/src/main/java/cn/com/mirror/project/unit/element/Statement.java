package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Statement implements Serializable {
    private Integer lineNum;
    private String content;
    private Set<Variable> varsInStat;
}
