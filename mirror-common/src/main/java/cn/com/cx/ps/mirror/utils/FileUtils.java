package cn.com.cx.ps.mirror.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piggy
 *
 * @description 
 * @since 2018年3月12日
 */
@Component
public class FileUtils {
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static final String getFileContent(String javaFile) {
        byte[] input = null;

        try {
            FileInputStream inputStream = new FileInputStream(javaFile);
            input = new byte[inputStream.available()];
            inputStream.read(input);
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", javaFile);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("File reading want wrong: {}", javaFile);
            e.printStackTrace();
        }
        log.info("File reading completed!");
        return new String(input);
    }


    public static final String getFileName(String javaFile){
        File file = new File(javaFile);
        String fileName = file.getName();
        log.info("Get file name for: {}", fileName);
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static final List<String> listCodeLines(String path) {
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(new File(path), "r");
        } catch (FileNotFoundException e) {
            log.error("File: " + path + " is not found!");
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<>();
        try {
            if (accessFile != null) {
                String line = accessFile.readLine();
                lines.add(line);
                while (line != null) {
                    line = accessFile.readLine();
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            log.error("Read file: " + path);
            e.printStackTrace();
        }
        return lines;
    }

}
