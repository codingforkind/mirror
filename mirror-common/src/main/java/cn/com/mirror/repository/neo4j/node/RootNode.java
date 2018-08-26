package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.NodeTypeEnum;
import cn.com.mirror.project.unit.element.Root;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
@Getter
@Setter
public class RootNode extends BaseNode {

    // full qualified name of the target
    @Property(name = "root")
    private String rootName;

    @Relationship(type = EdgeType.TYPE.TARGET_ROOT, direction = Relationship.INCOMING)
    private ClassNode targetNode;

    public RootNode(Integer startLineNum,
                    String targetPath,
                    Integer endLineNum,
                    String content,
                    String packageName,
                    String rootName) {

        super(startLineNum, targetPath, endLineNum, content, packageName, NodeTypeEnum.ROOT);
        this.rootName = rootName;
    }

    public static final RootNode instance(Root root) {
        Asserts.notNull(root, "Root element can not be null.");

        return new RootNode(root.getStartLineNum(),
                root.getTargetPath(),
                root.getEndLineNum(),
                root.getContent(),
                root.getPackageName(),
                root.getPackageName());
    }
}
