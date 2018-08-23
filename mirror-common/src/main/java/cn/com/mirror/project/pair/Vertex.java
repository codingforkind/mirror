package cn.com.mirror.project.pair;

import cn.com.mirror.constant.VertexTypeEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
@Getter
public class Vertex implements Serializable {

    private String targetPath;
    private int lineNum;
    private VertexTypeEnum vertexType;
    private String host;
    private int port;

    public Vertex(String targetPath,
                  int lineNum,
                  VertexTypeEnum vertexType,
                  String host,
                  int port) {

        this.targetPath = targetPath;
        this.lineNum = lineNum;
        this.vertexType = vertexType;
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return lineNum == vertex.lineNum &&
                Objects.equals(targetPath, vertex.targetPath) &&
                vertexType == vertex.vertexType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(targetPath, lineNum, vertexType);
    }
}
