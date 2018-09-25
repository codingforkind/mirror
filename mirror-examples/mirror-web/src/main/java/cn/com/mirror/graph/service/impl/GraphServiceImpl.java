package cn.com.mirror.graph.service.impl;

import cn.com.mirror.Mirror;
import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.graph.node.*;
import cn.com.mirror.graph.node.factory.NodeFactory;
import cn.com.mirror.graph.service.GraphService;
import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.element.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class GraphServiceImpl implements GraphService {

    @Override
    public Map<String, NodeFactory> mapVertex2GraphNode(String prjDir, boolean writeDB) {
        ProjectProperty projectProperty = new ProjectProperty();
        projectProperty.setUrl(prjDir);
        projectProperty.setEnableWriteGraphDB(Boolean.TRUE);

        Mirror mirror = new Mirror(projectProperty);

        Map<String, NodeFactory> nodeFactoryMap = new HashMap<>();
        // analyze the project
        for (Map.Entry<String, Map<Vertex, Set<Vertex>>> ctrlEdgeEntry : mirror.getPair().getCtrlEdges().entrySet()) {
            Map<Vertex, Set<Vertex>> edgeMap = ctrlEdgeEntry.getValue();

            // one target one node factory
            String targetPath = ctrlEdgeEntry.getKey();
            if (null == nodeFactoryMap.get(targetPath)) {
                nodeFactoryMap.put(targetPath, new NodeFactory());
            }

            for (Map.Entry<Vertex, Set<Vertex>> edges : edgeMap.entrySet()) {
                Vertex tailVtx = edges.getKey();
                Base tailBase = mirror.getBaseElement(tailVtx);

                Set<Vertex> headVtxSet = edges.getValue();
                for (Vertex headVtx : headVtxSet) {
                    Base headBase = mirror.getBaseElement(headVtx);
                    touchEdge(tailBase, headBase, nodeFactoryMap.get(targetPath));
                }
            }
        }
        // end
        if (writeDB) {
            write2GraphDB(nodeFactoryMap);
        }

        return nodeFactoryMap;
    }

    private void write2GraphDB(Map<String, NodeFactory> nodeFactoryMap) {
//        TODO xyz write it into graph db using springboot-neo4j
//        GraphEngine graphEngine = new GraphEngine();
//        for (String targetPath : nodeFactoryMap.keySet()) {
//            Map<Base, BaseNode> nodeCache = nodeFactoryMap.get(targetPath).getNodeCache();
//            for (BaseNode baseNode : nodeCache.values()) {
//                if (baseNode instanceof ClassNode) {
//                    graphEngine.write(baseNode);
//                }
//            }// mapVertex2GraphNode end
//        }
    }

    private void touchEdge(Base tailBase, Base headBase, NodeFactory nodeFactory) {
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

    @Override
    public Map<String, NodeFactory> mapAndWriteVertex2GraphNode(String prjDir) {
        return mapVertex2GraphNode(prjDir, true);
    }
}
