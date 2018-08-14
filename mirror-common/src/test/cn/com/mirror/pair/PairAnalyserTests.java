package cn.com.mirror.pair;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.project.pair.Pair;
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
    @Test
    public void test1() {
        PairAnalyser pairAnalyser = new PairAnalyser();
        Pair pair = pairAnalyser.analyze();
        Set<Map.Entry<String, Map<Integer, Integer>>> entries =
                pair.getDirectCtrlEdges().entrySet();
        entries.stream().forEach(entry -> {
//            String key = entry.getKey();
            log.info("Target: {}", entry.getKey());
            log.info("Pair: {}", entry.getValue());
        });
    }
}
