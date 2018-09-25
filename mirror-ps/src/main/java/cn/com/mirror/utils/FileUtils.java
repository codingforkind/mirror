package cn.com.mirror.utils;

import cn.com.mirror.exceptions.UnitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author Piggy
 * @description
 */
public class FileUtils {
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static Map<String, List<String>> lineCodesInUnit = new HashMap<>();


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
        return new String(input);
    }


    public static final String getFileName(String javaFile) {
        File file = new File(javaFile);
        String fileName = file.getName();
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static final List<String> listCodeLines(String path) {
        if (lineCodesInUnit.containsKey(path)) {
            return lineCodesInUnit.get(path);
        }

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

        addLineCodes(path, lines);

        return lines;
    }

    private static void addLineCodes(String path, List<String> lineCodes) {
        if (!lineCodesInUnit.containsKey(path)) {
            lineCodesInUnit.put(path, lineCodes);
        }
    }

    /**
     * Extract all the target in the archive
     */
    public static final Set<String> extractTargetPath(String prjPath) {
        File file = new File(prjPath);
        if (!file.exists() || !file.isDirectory()) {
            throw new UnitException(prjPath + " is not a directory or even not exists!");
        }

        return initjavaFiles(file, new HashSet<>());
    }

    private static Set<String> initjavaFiles(File dir, Set<String> prjJavaFiles) {
        File[] fs = dir.listFiles();
        String tmpPath = null;
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getAbsolutePath().endsWith(".java")) {
                tmpPath = fs[i].getAbsolutePath();
                prjJavaFiles.add(tmpPath);
            }
            if (fs[i].isDirectory()) {
                prjJavaFiles = initjavaFiles(fs[i], prjJavaFiles);
            } // end if
        } // end for
        return prjJavaFiles;
    }

}
