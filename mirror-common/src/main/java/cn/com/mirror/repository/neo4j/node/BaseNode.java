package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Base;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
public class BaseNode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "start line num")
    private Integer startLineNum;

    @Property(name = "end line num")
    private Integer endLineNum;

    @Property(name = "target path")
    private String targetPath;

    //    @Property(name = "details in this line")
    private String content;

    @Property(name = "package name")
    private String packageName;

    @Relationship(type = EdgeType.TYPE.CTRL_EDGE)
    private BaseNode ctrlDepNode;

    public BaseNode(Integer startLineNum,
                    String targetPath,
                    Integer endLineNum,
                    String content,
                    String packageName) {

        this.startLineNum = startLineNum;
        this.targetPath = targetPath;
        this.endLineNum = endLineNum;
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
