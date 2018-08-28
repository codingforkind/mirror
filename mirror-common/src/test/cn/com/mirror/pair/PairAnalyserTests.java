package cn.com.mirror.pair;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.exceptions.UnitException;
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

    private static final String TEST_FILE =
            "/home/piggy/work/mirror/mirror-common/src/main/java/cn/com/mirror/reflect/EdgeConstructor.java";

    @Test
    public void testDirectCtrlEdge() {
        PairAnalyser pairAnalyser = new PairAnalyser();
        Pair pair = pairAnalyser.analyze();
        for (Map.Entry<String, Map<Vertex, Set<Vertex>>> entry : pair.getCtrlEdges().entrySet()) {
            if (!TEST_FILE.equals(entry.getKey())) {
                // testing specific target
                continue;
            }

            log.debug("Target: {}", entry.getKey());
            for (Map.Entry<Vertex, Set<Vertex>> ver : entry.getValue().entrySet()) {
                System.out.println("TAIL: " +
                        ver.getKey().getLineNum() + " - " +
                        ver.getKey().getVertexType() + " - " +
                        "\t <- \t");
                for (Vertex vHead : ver.getValue()) {
                    if (!ver.getKey().getTargetPath().equals(vHead.getTargetPath())) {
                        throw new UnitException("SHIT");
                    }
                    System.out.println("HEAD: {" + vHead.getLineNum() + " - " + vHead.getVertexType() + "}");
                }
            }

        }
    }
}
