package cn.com.mirror;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.exceptions.ReflectException;
import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Base;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.project.code.LocalLoader;
import cn.com.mirror.project.graph.node.factory.NodeFactory;
import cn.com.mirror.project.graph.node.*;
import cn.com.mirror.project.graph.storage.GraphEngine;
import cn.com.mirror.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Slf4j
public class Mirror {
    private ProjectProperty projectProperty;

    private Unit unit;
    private Pair pair;
    private Map<String, NodeFactory> nodeFactoryMap;

    private final Integer poolSize = 2;
    private ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

    public Mirror() {
        this(LocalLoader.getPrjProperty());
    }

    public Mirror(ProjectProperty projectProperty) {
        this.nodeFactoryMap = new HashMap<>();
        this.projectProperty = projectProperty;

        CountDownLatch countDownLatch = new CountDownLatch(poolSize);
        executorService.execute(() -> {
            UnitAnalyser unitAnalyser = new UnitAnalyser();
            unit = unitAnalyser.analyze(projectProperty);
            countDownLatch.countDown();
        });
        executorService.execute(() -> {
            PairAnalyser pairAnalyser = new PairAnalyser();
            pair = pairAnalyser.analyze(projectProperty);
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    // mapping vertex to graph node
    public void mappingVertex2GraphNode() {
        // analyze the project
        for (Map.Entry<String, Map<Vertex, Set<Vertex>>> ctrlEdgeEntry : pair.getCtrlEdges().entrySet()) {
            Map<Vertex, Set<Vertex>> edgeMap = ctrlEdgeEntry.getValue();

            // one target one node factory
            String targetPath = ctrlEdgeEntry.getKey();
            if (null == this.nodeFactoryMap.get(targetPath)) {
                this.nodeFactoryMap.put(targetPath, new NodeFactory());
            }

            for (Map.Entry<Vertex, Set<Vertex>> edges : edgeMap.entrySet()) {
                Vertex tailVtx = edges.getKey();
                Base tailBase = getBaseElement(tailVtx);

                Set<Vertex> headVtxSet = edges.getValue();
                for (Vertex headVtx : headVtxSet) {
                    Base headBase = getBaseElement(headVtx);
                    touchEdge(tailBase, headBase);
                }
            }

            if (this.projectProperty.getEnableWriteGraphDB()) {
                write2GraphDB(targetPath);
            }

        }
        // end

    }

    private void write2GraphDB(String targetPath) {
        // mappingVertex2GraphNode
        GraphEngine graphEngine = new GraphEngine();
        Map<Base, BaseNode> nodeCache = this.nodeFactoryMap.get(targetPath).getNodeCache();
        for (BaseNode baseNode : nodeCache.values()) {
//                if (baseNode instanceof ClassNode) {
            graphEngine.write(baseNode);
//                }
        }// mappingVertex2GraphNode end
    }

    private void touchEdge(Base tailBase, Base headBase) {
        NodeFactory nodeFactory = this.nodeFactoryMap.get(tailBase.getTargetPath());
        BaseNode tailNode = nodeFactory.newNode(tailBase);
        BaseNode headNode = nodeFactory.newNode(headBase);

        switch (tailNode.getElementTypeEnum()) {
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
                    tmTail.addFieldNode((StatementNode) headNode);
                }
                break;
            }
            case METHOD: {
                MethodNode tmTail = (MethodNode) tailNode;
                tmTail.addStatementNode((StatementNode) headNode);
                break;
            }
            case STATEMENT: {
                StatementNode tmTail = (StatementNode) tailNode;
                tmTail.addStatementNode((StatementNode) headNode);
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
