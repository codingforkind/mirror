package cn.com.mirror.analysis.service.impl;

import cn.com.mirror.Mirror;
import cn.com.mirror.analysis.service.AnalysisService;
import cn.com.mirror.project.config.ProjectProperty;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Override
    public void analysis(String prjDir) {
        ProjectProperty projectProperty = new ProjectProperty();
        projectProperty.setUrl(prjDir);
        projectProperty.setEnableWriteGraphDB(Boolean.TRUE);

        Mirror mirror = new Mirror(projectProperty);
        mirror.mappingVertex2GraphNode();
    }
}
