package cn.com.mirror.analysis.service.impl;

import cn.com.mirror.analysis.service.AnalysisService;
import cn.com.mirror.analysis.service.AsyncAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncAnalysisServiceImpl implements AsyncAnalysisService {

    @Autowired
    private AnalysisService analysisService;

    @Async
    @Override
    public void asyncAnalyze(String prjDir) {
        analysisService.analysis(prjDir);
    }
}
