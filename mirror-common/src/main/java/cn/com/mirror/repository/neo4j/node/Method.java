/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.utils.BeanUtils;
import org.eclipse.jdt.core.dom.ASTNode;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
public class Method extends Class {
    private static final long serialVersionUID = 1L;

    private String methodName;
    private Integer methodStartLineNum;
    private Integer methodEndLineNum;
    private ASTNode methodContent;

    Method() {
    }

    Method(String methodName,
           Integer methodStartLineNum,
           Integer methodEndLineNum,
           ASTNode methodContent) {

        this.methodName = methodName;
        this.methodStartLineNum = methodStartLineNum;
        this.methodEndLineNum = methodEndLineNum;
        this.methodContent = methodContent;
    }

    public static Method instance(Class classNode,
                                  String methodName,
                                  Integer methodStartLineNum,
                                  Integer methodEndLineNum,
                                  ASTNode methodContent) {

        Method method = new Method(methodName, methodStartLineNum, methodEndLineNum, methodContent);
        BeanUtils.copyProperties(method, classNode);
        return method;
    }

    public static Method instance(Class classNode) {
        Method method = (Method) JSON.parse(JSON.toJSONString(classNode));
        return method;
    }
}
