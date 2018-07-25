package cn.com.mirror.project;

import cn.com.mirror.variable.Class;
import lombok.Data;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Data
public class Archive implements Serializable {

    private String path; // archive's location
    private Set<String> targets; // target files in the archive, such as java file in a java project.

    private Map<String, Set<Class>> classes; // all classes in this java archive


    public void addClasses(String targetPath, Set<Class> clsSet) {
        Validate.notEmpty(targetPath, "Target's path can not be null.");
        Validate.notEmpty(clsSet, "Target has none classes.");
        if (null != classes) {
            classes.put(targetPath, clsSet);
        } else {
            classes = new HashMap<>();
            classes.put(targetPath, clsSet);
        }
    }
}