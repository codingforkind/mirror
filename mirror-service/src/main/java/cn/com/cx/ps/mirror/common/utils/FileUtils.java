package cn.com.cx.ps.mirror.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);


    public static final String getFileContent(String javaFile) {
        byte[] input = null;

        try {
            FileInputStream inputStream = new FileInputStream(javaFile);
            input = new byte[inputStream.available()];
            inputStream.read(input);
            inputStream.close();
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", javaFile);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("File reading want wrong: {}", javaFile);
            e.printStackTrace();
        }
        logger.info("File reading completed!");
        return new String(input);
    }


    public static final String getFileName(String javaFile){
        File file = new File(javaFile);
        String fileName = file.getName();
        logger.info("Get file name for: {}", fileName);
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

}
