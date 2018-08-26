package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.exceptions.EdgeTouchException;
import cn.com.mirror.exceptions.ReflectException;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.*;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.repository.neo4j.node.*;
import cn.com.mirror.repository.neo4j.storage.GraphEngine;
import cn.com.mirror.utils.FileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Data
@Slf4j
public class EdgeConstructor {

    private Unit unit;
    private Pair pair;

    public void construct() {
        // analyze the project
        PairAnalyser pairAnalyser = new PairAnalyser();
        UnitAnalyser unitAnalyser = new UnitAnalyser();

        unit = unitAnalyser.analyze();
        pair = pairAnalyser.analyze();

        // construct
        GraphEngine graphEngine = new GraphEngine();

        for (Map.Entry<String, Map<Vertex, Vertex>> ctrlEdgeEntry : pair.getCtrlEdges().entrySet()) {
            String targetPath = ctrlEdgeEntry.getKey();

            // package is the root node of the target
            Root root = unit.getRoot(unit.getPackages().get(targetPath));
            RootNode rootNode = RootNode.instance(root);

            Map<Vertex, Vertex> ctrlEdge = ctrlEdgeEntry.getValue();

            ctrlEdge.forEach((ctrlKey, ctrlVal) -> {
                Base headB = getBaseElement(ctrlKey);
                Base tailB = getBaseElement(ctrlVal);

                if (null == headB || null == tailB) {
                    log.debug("TARGET: {}", targetPath);
                    log.debug("HEAD: {}:{} \t->\t TAIL: {}:{}", ctrlKey.getLineNum(),
                            ctrlKey.getVertexType(), ctrlVal.getLineNum(), ctrlVal.getVertexType());

                    throw new EdgeTouchException("Head statement or tail statement is null! \tHEAD: {"
                            + headB + "} -> TAIL: {" + tailB + "}");
                }

                BaseNode newGraphNodeTail = touch(headB, tailB);

                // TODO xyz add edge to rootNode
            });
            graphEngine.write(rootNode);
        }
    }

    /**
     * Convert elements to graph nodes and construct direct edge between them.
     *
     * @param headB head in the edge
     * @param tailB tail in the edge
     * @return The tail in the edge
     */
    public BaseNode touch(Base headB, Base tailB) {
        // tail adds head into its properties
        // TODO xyz tail factory to generate tailNodes (same tail has different head, find the common tail)

        if (tailB instanceof Class) {
            ClassNode tailNode = ClassNode.instance((Class) tailB);
            if (headB instanceof Class) {
                // class to class
                ClassNode headNode = ClassNode.instance((Class) headB);
                tailNode.touchClassNode(headNode);
            } else if (headB instanceof Method) {
                // method to class
                MethodNode headNode = MethodNode.instance((Method) headB);
                tailNode.touchMethodNode(headNode);
            }
            return tailNode;
        }

        if (tailB instanceof Method) {
            // statement to method
            MethodNode tailNode = MethodNode.instance((Method) tailB);
            StatementNode headNode = StatementNode.instance((Statement) headB);
            tailNode.touchStatementNode(headNode);
            return tailNode;
        }

        // statement to statement
        StatementNode tailNode = StatementNode.instance((Statement) tailB);
        StatementNode headNode = StatementNode.instance((Statement) headB);
        tailNode.setCtrlDepNode(headNode);
        return tailNode;
    }

    private Base getBaseElement(Vertex vertex) {
        switch (vertex.getVertexType()) {
            case CLASS: {
                Set<Class> classes = unit.getClasses().get(vertex.getTargetPath());
                for (Class cls : classes) {
                    if (cls.getStartLineNum() <= vertex.getLineNum()
                            && vertex.getLineNum() <= cls.getEndLineNum()) {
                        return cls;
                    }
                }

                break;
            }

            case METHOD: {
                Set<Method> methods = unit.getMethods().get(vertex.getTargetPath());
                for (Method mtd : methods) {
                    if (mtd.getStartLineNum() <= vertex.getLineNum()
                            && vertex.getLineNum() <= mtd.getEndLineNum()) {
                        return mtd;
                    }
                }
                break;
            }

            case FIELD:
            case STATEMENT: {
                Statement statement = unit.getStatements().get(vertex.getTargetPath()).get(vertex.getLineNum());
                if (null != statement) {
                    return statement;
                }

                log.warn("Statement: " + vertex.getLineNum()
                        + "-" + vertex.getVertexType()
                        + " for target: {" + vertex.getTargetPath() + "}"
                        + " is not retrieved from the variable visitor.");

                statement = new Statement(vertex.getTargetPath(),
                        vertex.getLineNum(),
                        vertex.getLineNum(),
                        FileUtils.listCodeLines(vertex.getTargetPath()).get(vertex.getLineNum() - 1),
                        unit.getPackages().get(vertex.getTargetPath()));
                return statement;
            }

            default:
                break;
        }

        throw new ReflectException("Can not generate base element.");
    }
}
