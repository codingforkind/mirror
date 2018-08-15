package cn.com.mirror.project.unit.element;

import lombok.Data;

import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Statement extends Method {
    private Integer statLineNum;
    private String statContent;
    private Set<Variable> varsInStat;
}
