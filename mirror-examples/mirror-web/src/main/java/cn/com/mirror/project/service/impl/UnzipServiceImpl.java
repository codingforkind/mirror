package cn.com.mirror.project.service.impl;

import cn.com.mirror.constant.ArchiveTypeEnum;
import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.nas.config.NasProperties;
import cn.com.mirror.project.service.UnzipService;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UnzipServiceImpl implements UnzipService {

    @Autowired
    private NasProperties nasProperties;


    @Override
    public String extractZippedSrcFile(String prjName, String filePath, ArchiveTypeEnum archiveType) {
        String dest = nasProperties.getLocation() + "tmpDir" + File.separator + prjName;

        switch (archiveType) {
            case _ZIP: {
                try {
                    ZipFile zipFile = new ZipFile(filePath);
                    if (zipFile.isEncrypted()) {
                        throw new UnitException("File is encrypted.");
                    }
                    zipFile.extractAll(dest);
                } catch (ZipException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                break;
        }

        return dest;
    }


}
