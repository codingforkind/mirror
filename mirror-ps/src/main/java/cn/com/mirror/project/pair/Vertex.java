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
    private String ip;
    private int port;

    public Vertex(String targetPath,
                  int lineNum,
                  VertexTypeEnum vertexType,
                  String ip,
                  int port) {

        this.targetPath = targetPath;
        this.lineNum = lineNum;
        this.vertexType = vertexType;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return lineNum == vertex.lineNum &&
                port == vertex.port &&
                Objects.equals(targetPath, vertex.targetPath) &&
                vertexType == vertex.vertexType &&
                Objects.equals(ip, vertex.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetPath, lineNum, vertexType, ip, port);
    }
}
