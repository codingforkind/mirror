package cn.com.cx.ps.mirror.common.visitor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.common.utils.AstUtils;
import cn.com.cx.ps.mirror.java.variable.Variable;
import cn.com.cx.ps.mirror.java.variable.VariableType;
import cn.com.cx.ps.mirror.project.MirrorProject;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Variable visitor.
 * visit all SimpleNodes and resolve its binding
 * and extract the variables for each line of codes
 */
@Getter
@Setter
public class VariableVisitor extends ASTVisitor {

    private static Logger log = LoggerFactory.getLogger(VariableVisitor.class);
    private String file;
    private MirrorProject mirrorProject;

    private Set<Variable> variables = new HashSet<>();

    @Override
    public boolean visit(SimpleName node) {
        IBinding binding = node.resolveBinding();
        if (binding instanceof IVariableBinding) {
            IVariableBinding varTypeBinding = (IVariableBinding) binding;
            Variable variable = new Variable();
            variable.setAstNode(node);
            variable.setFile(file);
            variable.setLineNum(AstUtils.getEndLine(node));
            variable.setName(varTypeBinding.getName());
            variable.setField(varTypeBinding.isField());

            if ("filePath".equals(varTypeBinding.getName())) {
                log.info("name: {}, {}", varTypeBinding.getName(), varTypeBinding.getType().getName());
                log.info("lineNUM:{}", AstUtils.getEndLine(node));
                variableTypeProcess(varTypeBinding.getType());
            }
//            Variable type handle AND TYPE ONLY
//            variable.setVariableType(variableTypeProcess(varTypeBinding.getType()));
            variables.add(variable);
        }
        return super.visit(node);
    }

    /**
     * check the qualified class name is included in this project, if not then skip it
     * if so just initializing this class variable type into the project object.
     */
    private VariableType variableTypeProcess(ITypeBinding varTypeBinding) {
        VariableType varType = new VariableType();
        if (varTypeBinding.isPrimitive()) {
            varType.setType(VariableType.TYPE.PRIMITIVE);
            return primitiveType(varTypeBinding.getName());
        }

        if (varTypeBinding.isEnum()) {
            varType.setType(VariableType.TYPE.ENUM);
            varType.setEnumType(varTypeBinding.getQualifiedName());
            return varType;
        }

        if (varTypeBinding.isClass()) {
//            Class variable
            varType.setType(VariableType.TYPE.CLASS);
            String test = varTypeBinding.getQualifiedName();
            if (mirrorProject.classInProject(test)) {
                varType.setClassType(varTypeBinding.getQualifiedName());
            } else {
                varType.setOtherClass(varTypeBinding.getQualifiedName());
            }
//            TODO analyzing the methods declared in this class type.
            return varType;
        }

        if (varTypeBinding.isArray()) {
            varType.setType(VariableType.TYPE.ARRAY);
            StringBuilder builder = new StringBuilder(varTypeBinding.getQualifiedName());
            String tmType = builder.substring(0, builder.lastIndexOf("["));
            VariableType eleType = new VariableType();
            if (mirrorProject.classInProject(tmType)) {
                eleType.setType(VariableType.TYPE.CLASS);
                eleType.setClassType(tmType);
            }

            eleType = primitiveType(tmType);
            if (null != eleType) {
                varType.setArrayElementType(eleType);
            } else {
                eleType.setType(VariableType.TYPE.OTHER);
                eleType.setOther(tmType);
            }
            varType.setArrayElementType(eleType);
            return varType;
        }


        if (varTypeBinding.isGenericType()) {
            log.info("\t GenericType: {}, {}, {}",
                    varTypeBinding.getBinaryName(), varTypeBinding.getName(), varTypeBinding.getQualifiedName());

            return varType;
        }

        if (varTypeBinding.isInterface()) {
            varType.setType(VariableType.TYPE.INTERFACE);
            varType.setInterfaceType(varTypeBinding.getQualifiedName());
            return varType;
        }

        if (varTypeBinding.isIntersectionType()) {
//java.util.List, List<?>, java.util.List<?>
            log.info("intersectionType: {}", varTypeBinding);
            return varType;
        }

        log.warn("VARIABLE NOT ANALYSIS: [{}]", varTypeBinding);
        return null;
    }

    private VariableType primitiveType(String type) {
        VariableType varType = new VariableType();
        switch (type) {
            case "int":
                varType.setPrimeType(VariableType.PRIME.INT);
                break;

            case "float":
                varType.setPrimeType(VariableType.PRIME.FLOAT);
                break;
            case "double":
                varType.setPrimeType(VariableType.PRIME.DOUBLE);
                break;
            case "short":
                varType.setPrimeType(VariableType.PRIME.SHORT);
                break;
            case "long":
                varType.setPrimeType(VariableType.PRIME.LONG);
                break;
            case "boolean":
                varType.setPrimeType(VariableType.PRIME.BOOLEAN);
                break;
            case "byte":
                varType.setPrimeType(VariableType.PRIME.BYTE);
                break;
            case "char":
                varType.setPrimeType(VariableType.PRIME.CHAR);
                break;
        }
        return varType;
    }

}
