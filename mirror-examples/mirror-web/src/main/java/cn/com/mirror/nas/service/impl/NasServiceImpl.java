package cn.com.mirror.nas.service.impl;

import cn.com.mirror.nas.config.NasProperties;
import cn.com.mirror.nas.service.NasService;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class NasServiceImpl implements NasService {

    @Autowired
    private NasProperties nasProperties;

    @Override
    public String uploadArchive(byte[] content) {
        Asserts.notEmpty(nasProperties.getLocation(), "File storage location can not be empty");

        String fileLocation = nasProperties.getLocation();
        String fileName = "tmpName.txt";
        String filePath = fileLocation + File.separator + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
            outputStream.write(content);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return filePath;
    }


}
