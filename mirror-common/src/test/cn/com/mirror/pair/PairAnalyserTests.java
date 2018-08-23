package cn.com.mirror.pair;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
@Slf4j
public class PairAnalyserTests {

    private final String TEST_FILE =
            "/home/piggy/work/mirror/mirror-common/src/main/java/cn/com/mirror/analyser/visitor/VariableVisitor.java";

    @Test
    public void test1() {
        PairAnalyser pairAnalyser = new PairAnalyser();
        Pair pair = pairAnalyser.analyze();
        Set<Map.Entry<String, Map<Integer, Integer>>> entries = pair.getDirectCtrlEdges().entrySet();
        entries.stream().forEach(entry -> {
            if (TEST_FILE.equals(entry.getKey())) {
                log.info("Target: {}", entry.getKey());
                log.info("Pair: {}", entry.getValue());
                return;
            }
        });
    }


    @Test
    public void test2() {
        PairAnalyser pairAnalyser = new PairAnalyser();
        Pair pair = pairAnalyser.analyze();
        for (Map.Entry<String, Map<Vertex, Vertex>> entry : pair.getCtrlEdges().entrySet()) {
            if (TEST_FILE.equals(entry.getKey())) {
                log.debug("Target: {}", entry.getKey());

                for (Map.Entry<Vertex, Vertex> ver : entry.getValue().entrySet()) {
                    System.out.println("HEAD: " +
                            ver.getKey().getLineNum() + " - " +
                            ver.getKey().getVertexType() + "\t->\t" + "TAIL: " +
                            ver.getValue().getLineNum() + ", " +
                            ver.getValue().getVertexType());
                }

                return;
            }
        }
    }
}
