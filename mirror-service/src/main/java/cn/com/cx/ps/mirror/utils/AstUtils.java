package cn.com.cx.ps.mirror.utils;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piggy on 2017/7/20.
 */
public class AstUtils {
    private static Logger logger = LoggerFactory.getLogger(AstUtils.class);

    public static int getEndLine(ASTNode node) {
        return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition() + node.getLength() - 1);
    }

    public static final CompilationUnit getCompUnitResolveBinding(String javaFile) {
        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        astParser.setEnvironment(null, null, null, true);
        astParser.setUnitName(FileUtils.getFileName(javaFile));
        astParser.setResolveBindings(true);
        astParser.setBindingsRecovery(true);
        String content = FileUtils.getFileContent(javaFile);
        astParser.setSource(content.toCharArray());

        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
        return result;
    }


    public static final List<String> listCodeLines(String path) {
        ArrayList<String> lines = new ArrayList<>();
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(new File(path), "r");
        } catch (FileNotFoundException e) {
            logger.error("File: " + path + " is not found!");
            e.printStackTrace();
        }
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
            logger.error("Read file: " + path);
            e.printStackTrace();
        }
        return lines;
    }

}
