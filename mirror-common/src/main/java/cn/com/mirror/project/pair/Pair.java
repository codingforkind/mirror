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

    public void addDirectCtrlEdges(String targetPath, Map<Integer, Integer> ctrlEdges) {
        this.directCtrlEdges.put(targetPath, ctrlEdges);
    }
}