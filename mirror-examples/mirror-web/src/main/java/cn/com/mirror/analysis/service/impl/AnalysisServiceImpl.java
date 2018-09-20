package cn.com.mirror.analysis.service.impl;

import cn.com.mirror.analysis.service.AnalysisService;
import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.reflect.EdgeConstructor;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Override
    public void analysis(String prjDir) {
        ProjectProperty projectProperty = new ProjectProperty();
        projectProperty.setUrl(prjDir);
        projectProperty.setEnableWriteGraphDB(Boolean.TRUE);

        EdgeConstructor edgeConstructor = new EdgeConstructor(projectProperty);
        edgeConstructor.construct();
    }
}
