package cn.com.mirror.project.pair;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
@Getter
public class Pair {
    private Map<String, Map<Integer, Integer>> directCtrlEdges = new HashMap<>();
    private Map<String, Map<Vertex, Vertex>> ctrlEdges = new HashMap<>();

    public void addDirectCtrlEdges(String targetPath, Map<Integer, Integer> ctrlEdges) {
        this.directCtrlEdges.put(targetPath, ctrlEdges);
    }

    public void addCtrlEdge(String targetPath, Map<Vertex, Vertex> ctrlEdges) {
        this.ctrlEdges.put(targetPath, ctrlEdges);
    }
}
