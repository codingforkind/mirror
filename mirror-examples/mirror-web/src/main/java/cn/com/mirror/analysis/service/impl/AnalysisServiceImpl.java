package cn.com.mirror.analysis.service.impl;

import cn.com.mirror.analysis.service.AnalysisService;
import cn.com.mirror.graph.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private GraphService graphService;

    @Override
    public void analysis(String prjDir) {
        graphService.mapVertex2GraphNode(prjDir, false);
    }
}
