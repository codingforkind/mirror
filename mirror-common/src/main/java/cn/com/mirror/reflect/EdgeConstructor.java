package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.exceptions.ReflectException;
import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Base;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
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
    private NodeFactory nodeFactory = new NodeFactory();

    public void construct() {
        // analyze the project
        PairAnalyser pairAnalyser = new PairAnalyser();
        UnitAnalyser unitAnalyser = new UnitAnalyser();

        unit = unitAnalyser.analyze();
        pair = pairAnalyser.analyze();

        for (Map.Entry<String, Map<Vertex, Set<Vertex>>> ctrlEdgeEntry : pair.getCtrlEdges().entrySet()) {
            Map<Vertex, Set<Vertex>> edgeMap = ctrlEdgeEntry.getValue();

            for (Map.Entry<Vertex, Set<Vertex>> edges : edgeMap.entrySet()) {
                Vertex tailVtx = edges.getKey();
                Base tailBase = getBaseElement(tailVtx);

                Set<Vertex> headVtxSet = edges.getValue();
                for (Vertex headVtx : headVtxSet) {
                    Base headBase = getBaseElement(headVtx);
                    touchEdge(tailBase, headBase);
                }
            }
        }

        testConstruct();
    }

    private void testConstruct() {
        // construct
        GraphEngine graphEngine = new GraphEngine();
        Map<Base, BaseNode> nodeCache = nodeFactory.getNodeCache();
        for (BaseNode baseNode : nodeCache.values()) {
            if (baseNode instanceof ClassNode) {
                graphEngine.write(baseNode);
            }
        }
    }

    private void touchEdge(Base tailBase, Base headBase) {

        BaseNode tailNode = nodeFactory.newNode(tailBase);
        BaseNode headNode = nodeFactory.newNode(headBase);

        switch (tailNode.getNodeType()) {
            case ROOT: {
                RootNode tmTail = (RootNode) tailNode;
                tmTail.setTargetNode((ClassNode) headNode);
                break;
            }
            case CLASS: {
                ClassNode tmTail = (ClassNode) tailNode;
                if (headNode instanceof ClassNode) {
                    // class to class
                    tmTail.addClassNode((ClassNode) headNode);
                } else if (headNode instanceof MethodNode) {
                    // method to class
                    tmTail.addMethodNode((MethodNode) headNode);
                } else {
                    // field to class
                    tmTail.setCtrlDepNode(headNode);
                }
                break;
            }
            case METHOD: {
                MethodNode tmTail = (MethodNode) tailNode;
                tmTail.addStatementNode((StatementNode) headNode);
                break;
            }
            case STATEMENT: {
                tailNode.setCtrlDepNode(headNode);
                break;
            }
            default: {
                throw new UnitException("No match type node found, can not touch edges.");
            }
        }

        nodeFactory.updateNode(tailBase, tailNode);
//        nodeFactory.updateNode(headBase, headNode);
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
