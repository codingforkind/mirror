package cn.com.mirror.nas.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.nas.config.NasProperties;
import cn.com.mirror.nas.service.NasService;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class NasServiceImpl implements NasService {

    @Autowired
    private NasProperties nasProperties;

    @Override
    public String uploadArchive(String prjName, String postfix, byte[] content) {
        Asserts.notEmpty(nasProperties.getLocation(), "File storage location can not be empty");

        String fileName = prjName + "." + postfix;
        String filePath = nasProperties.getLocation() + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
            outputStream.write(content);
        } catch (IOException e) {
            // TODO xyz check it before the application started.
            throw new UnitException("The dir for uploaded file is not configured", e);
        }

        return filePath;
    }


}
