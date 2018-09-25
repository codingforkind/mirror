package cn.com.mirror.graph.service;

import cn.com.mirror.graph.node.factory.NodeFactory;

import java.util.Map;

public interface GraphService {

    /**
     * Mapping vertex to graph node
     */
    Map<String, NodeFactory> mapVertex2GraphNode(String prjDir, boolean writeDB);


    Map<String, NodeFactory> mapAndWriteVertex2GraphNode(String prjDir);
}
