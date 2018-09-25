package cn.com.mirror;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.exceptions.ReflectException;
import cn.com.mirror.project.code.LocalLoader;
import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.Vertex;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Base;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.utils.FileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
@Data
public class Mirror {
    private ProjectProperty projectProperty;

    private Unit unit;
    private Pair pair;

    private final Integer poolSize = 2;
    private ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

    public Mirror() {
        this(LocalLoader.getPrjProperty());
    }

    public Mirror(ProjectProperty projectProperty) {
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

    public Base getBaseElement(Vertex vertex) {
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
