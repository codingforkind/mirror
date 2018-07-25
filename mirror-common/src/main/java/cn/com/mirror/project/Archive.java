package cn.com.mirror.project;

import cn.com.mirror.variable.Class;
import lombok.Data;

import java.io.Serializable;
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

}