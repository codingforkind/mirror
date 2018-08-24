package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.exceptions.EdgeTouchException;
import cn.com.mirror.exceptions.ReflectException;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Base;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.repository.neo4j.node.BaseNode;
import cn.com.mirror.repository.neo4j.node.ClassNode;
import cn.com.mirror.repository.neo4j.node.MethodNode;
import cn.com.mirror.repository.neo4j.node.StatementNode;
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
        Map<String, Map<Integer, Statement>> mappedStatements = unit.getStatements();

        GraphEngine graphEngine = new GraphEngine();

        for (Map.Entry<String, Map<Vertex, Vertex>> ctrlEdgeEntry : pair.getCtrlEdges().entrySet()) {
            String targetPath = ctrlEdgeEntry.getKey();
            Map<Vertex, Vertex> ctrlEdge = ctrlEdgeEntry.getValue();

            // all element in the unit
            Map<Integer, Statement> varsInTarget = mappedStatements.get(targetPath);

            // basic control edges in the target file
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

                // TODO xyz generate start and end node for this target and write it into the graph db.
                BaseNode newGraphNodeTail = touch(headB, tailB);
                graphEngine.write(newGraphNodeTail);

            });
        }
    }

    /**
     * Convert elements to graph nodes and construct direct edge between them.
     *
     * @param headB head in the graph
     * @param tailB tail in the graph
     * @return
     */
    public BaseNode touch(Base headB, Base tailB) {
        if (headB instanceof Statement) {
            StatementNode headNode = StatementNode.instance((Statement) headB);
            if (tailB instanceof Class) {
                // field to class
                ClassNode tailNode = ClassNode.instance((Class) tailB);
                headNode.setToClass(tailNode);
            } else if (tailB instanceof Method) {
                // statement to statement
                MethodNode tailNode = MethodNode.instance((Method) tailB);
                headNode.setToMethod(tailNode);
            } else {
                // statement to statement
                StatementNode tailNode = StatementNode.instance((Statement) tailB);
                headNode.setCtrlDepNode(tailNode);
            }

            return headNode;
        }

        // method to class
        MethodNode headNode = MethodNode.instance((Method) headB);
        ClassNode tailNode = ClassNode.instance((Class) tailB);
        headNode.setCls(tailNode);
        return headNode;
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
