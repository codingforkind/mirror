package cn.com.mirror.nas.service.impl;

import cn.com.mirror.nas.service.AsyncNasService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncNasServiceImpl implements AsyncNasService {


    @Async
    @Override
    public void unzipArchive(String filePath) {

    }


}
