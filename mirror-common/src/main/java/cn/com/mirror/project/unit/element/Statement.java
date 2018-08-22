package cn.com.mirror.project.unit.element;

import cn.com.mirror.project.unit.element.variable.Variable;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author piggy
 * @description
 * @date 18-8-15
 */
@Data
public class Statement extends Base {
    private Set<Variable> varsInStat;

    private boolean mtdDecFlag;
    private Method inMethod;

    public Statement(String targetPath,
                     Integer startLineNum,
                     Integer endLineNum,
                     String content,
                     String packageName,
                     boolean mtdDecFlag) {

        super(targetPath, startLineNum, endLineNum, content, packageName);

        this.varsInStat = new TreeSet<>();
        this.mtdDecFlag = mtdDecFlag;
    }


}
