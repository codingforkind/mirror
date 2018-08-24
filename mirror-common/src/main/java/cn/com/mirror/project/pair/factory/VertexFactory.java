package cn.com.mirror.project.pair.factory;

import cn.com.mirror.constant.VertexTypeEnum;
import cn.com.mirror.exceptions.GenerateVertexException;
import cn.com.mirror.project.pair.Vertex;
import lombok.Data;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
@Data
public class VertexFactory {
    private Map<String, Vertex> distributedVertex = new HashMap<>();

    private String getHostKey(String ip, int port, String targetPath, int lineNum) {
        String hostKey = null;
        if (null != ip && -1 != port) {
            // vertex are distributed in the system
            hostKey = ip + ":" + port + ":" + targetPath.hashCode() + ":" + lineNum;
        } else {
            // vertex are in the localhost
            hostKey = "LOCALHOST:-1" + ":" + targetPath.hashCode() + ":" + lineNum;
        }
        hostKey.intern();
        return hostKey;
    }

    private VertexTypeEnum checkVertexType(ASTNode node) {
        if (node instanceof TypeDeclaration) return VertexTypeEnum.CLASS;
        if (node instanceof MethodDeclaration) return VertexTypeEnum.METHOD;
        if (node instanceof FieldDeclaration) return VertexTypeEnum.FIELD;
        return VertexTypeEnum.STATEMENT;
    }

    public Vertex getVertex(String ip, int port, String targetPath, int lineNum) {
        return this.distributedVertex.get(
                getHostKey(ip, port, targetPath, lineNum));
    }

    public Vertex genVertex(String targetPath, int curLine, ASTNode node) {
        return genVertex(targetPath, curLine, node, null, -1);
    }

    public Vertex genVertex(String targetPath, int curLine, ASTNode node, String ip, int port) {
        if (null != ip && -1 == port) {
            throw new GenerateVertexException("Port can not be -1.");
        }

        if (-1 != port && null == ip) {
            throw new GenerateVertexException("IP address can not be null");
        }

        String hostKey = getHostKey(ip, port, targetPath, curLine);
        Vertex vertex = this.distributedVertex.get(hostKey);
        if (null == vertex) {
            vertex = new Vertex(targetPath, curLine, checkVertexType(node), ip, port);
            this.distributedVertex.put(hostKey, vertex);
        }

        return vertex;
    }

}
