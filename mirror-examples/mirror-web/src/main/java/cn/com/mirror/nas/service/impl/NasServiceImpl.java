package cn.com.mirror.nas.service.impl;

import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.nas.config.NasProperties;
import cn.com.mirror.nas.service.NasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class NasServiceImpl implements NasService {

    @Autowired
    private NasProperties nasProperties;

    @Override
    public String uploadArchive(String prjName, String postfix, byte[] content) {

        String fileName = prjName + "." + postfix;
        String filePath = nasProperties.getLocation() + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
            outputStream.write(content);
        } catch (IOException e) {
            throw new UnitException("The dir for uploaded file is not configured", e);
        }

        return filePath;
    }


}
