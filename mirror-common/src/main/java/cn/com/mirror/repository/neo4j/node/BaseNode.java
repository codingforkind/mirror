package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Base;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
@NodeEntity(label = "a node in target which contains some basic information")
public class BaseNode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "start line num")
    private Integer fromLineNum;

    @Property(name = "end line num")
    private Integer toLineNum;

    @Property(name = "target path")
    private String targetPath;

    @Property(name = "details in this line")
    private String content;

    @Property(name = "package name")
    private String packageName;

    @Relationship(type = EdgeType.TYPE.CTRL_EDGE)
    private BaseNode ctrlDepNode;

    public BaseNode(Integer fromLineNum,
                    String targetPath,
                    Integer toLineNum,
                    String content,
                    String packageName) {

        this.fromLineNum = fromLineNum;
        this.targetPath = targetPath;
        this.toLineNum = toLineNum;
        this.content = content;
        this.packageName = packageName;
    }

    public static final BaseNode instance(Base base) {
        Asserts.notNull(base, "Base element can not be null.");

        return new BaseNode(base.getStartLineNum(),
                base.getTargetPath(),
                base.getEndLineNum(),
                base.getContent(),
                base.getPackageName());
    }

}
