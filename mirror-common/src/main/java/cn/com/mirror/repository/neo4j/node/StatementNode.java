/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.ElementTypeEnum;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Statement nodes are the leafs in the graph
 *
 * @author Piggy
 * @description
 */
@NodeEntity(label = "statement")
public class StatementNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    StatementNode(Integer startLineNum,
                  String targetPath,
                  Integer endLineNum,
                  String content,
                  String packageName,
                  ElementTypeEnum nodeType) {

        super(startLineNum, targetPath, endLineNum, content, packageName, nodeType);
    }
}
