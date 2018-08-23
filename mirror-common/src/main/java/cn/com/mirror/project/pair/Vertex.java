package cn.com.mirror.project.pair;

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

    public Vertex(String targetPath, int lineNum, VertexTypeEnum vertexType) {
        this.targetPath = targetPath;
        this.lineNum = lineNum;
        this.vertexType = vertexType;
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
