package cn.com.mirror.analyser.visitor;

import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.project.unit.element.variable.Variable;
import cn.com.mirror.project.unit.element.variable.VariableType;
import cn.com.mirror.project.unit.element.variable.VariableType.PRIME;
import cn.com.mirror.project.unit.element.variable.VariableType.TYPE;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.*;

import java.util.*;
import java.util.Map.Entry;

/**
 * The type Variable visitor. visit all SimpleNodes and resolve its binding and
 * extract the variables for each line of codes
 * <p>
 * Extract all the variables in the project file.
 */
@Getter
@Setter
@Slf4j
public class VariableVisitor extends ASTVisitor {
    private final String file;
    private final String packageName;
    /**
     * all classes defined in the project
     */
    private final Map<String, Set<Class>> unitClasses;
    private final Set<Method> targetMethods;

    private Set<Variable> variableSet = new HashSet<>(); // all element defined in this project file
    private Map<Integer, Statement> varStatMap = new TreeMap<>();

    public VariableVisitor(String file,
                           String packageName,
                           Map<String, Set<Class>> unitClasses,
                           Set<Method> methods) {

        this.file = file;
        this.packageName = packageName;
        this.unitClasses = unitClasses;
        this.targetMethods = methods;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        unitClasses.get(this.file).stream().forEach(cls -> {
            if (null != cls.getCls(node)) {
                // found cls
                Arrays.stream(node.getFields()).forEach(fieldDeclaration -> {
                    fieldDeclaration.fragments().stream().forEach(varDec -> {
                        if (varDec instanceof VariableDeclarationFragment) {
                            VariableDeclarationFragment fragment = (VariableDeclarationFragment) varDec;
                            SimpleName name = fragment.getName();
                            IBinding iBinding = name.resolveBinding();
                            if (iBinding instanceof IVariableBinding) {
                                IVariableBinding iVariableBinding = (IVariableBinding) iBinding;
                                Variable field = genVariable(name, iVariableBinding);
                                cls.addField(field);
                            }
                        }
                    });
                });
            }
        });

        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        targetMethods.stream().forEach(mtd -> {
            if (null != mtd.getMtd(node)) {
                node.parameters().stream().forEach(param -> {
                    if (param instanceof SingleVariableDeclaration) {
                        SingleVariableDeclaration single = (SingleVariableDeclaration) param;
                        SimpleName name = single.getName();
                        IBinding iBinding = name.resolveBinding();
                        if (iBinding instanceof IVariableBinding) {
                            IVariableBinding iVariableBinding = (IVariableBinding) iBinding;
                            Variable paramVar = genVariable(name, iVariableBinding);
                            mtd.addParam(paramVar);
                        }
                    }
                });
            }
        });
        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleName node) {
        IBinding binding = node.resolveBinding();
        if (binding instanceof IVariableBinding) {
            IVariableBinding iVariableBinding = (IVariableBinding) binding;
            Variable variable = genVariable(node, iVariableBinding);
            addVariable(AstUtils.getStartLine(node), variable);

            variableSet.add(variable);
        }
        return super.visit(node);
    }


    // private methods
    private void addVariable(Integer lineNum,
                             Variable variable) {

        if (!varStatMap.containsKey(lineNum)) {
            Statement statement = new Statement(this.file,
                    lineNum,
                    lineNum,
                    FileUtils.listCodeLines(this.file).get(lineNum - 1),
                    this.packageName);

            statement.addVariable(variable);
            varStatMap.put(lineNum, statement);
        } else {
            varStatMap.get(lineNum).addVariable(variable);
        }
    }


    private Variable genVariable(SimpleName node, IVariableBinding iVariableBinding) {
        Variable variable = new Variable();
        variable.setAstNode(node);
        variable.setFile(file);
        variable.setLineNum(AstUtils.getStartLine(node));
        variable.setName(iVariableBinding.getName());
        variable.setFieldFlag(iVariableBinding.isField());
        variable.setParamFlag(iVariableBinding.isParameter());

        // Variable type handle AND TYPE ONLY
        variable.setVariableType(analysisVariableType(iVariableBinding.getType()));

        return variable;
    }

    /**
     * check the qualified class name is included in this project, if not then skip
     * it if so just initializing this class element type into the project object.
     */
    private VariableType analysisVariableType(ITypeBinding typeBinding) {
        VariableType varType = null;
        switch (VariableType.TYPE.judgeType(typeBinding)) {
            case PRIME:
                varType = new VariableType(TYPE.PRIME, PRIME.prime(typeBinding.getName()));
                break;

            case CLASS:
                String clsTypeQualifiedName = typeBinding.getQualifiedName();

                if (classDefinedInProject(unitClasses, clsTypeQualifiedName)) {
                    varType = new VariableType(TYPE.CLASS, clsTypeQualifiedName);
                } else {
                    varType = new VariableType(TYPE.OTHER, clsTypeQualifiedName);
                }
                break;

            case INTERFACE:
                varType = new VariableType(TYPE.INTERFACE, typeBinding.getQualifiedName());
                break;
            case ENUM:
                varType = new VariableType(TYPE.ENUM, typeBinding.getQualifiedName());
                break;

            case ARRAY:
                StringBuilder builder = new StringBuilder(typeBinding.getQualifiedName());
                String tmType = builder.substring(0, builder.lastIndexOf("["));

                VariableType eleType = new VariableType(TYPE.OTHER);
                if (classDefinedInProject(unitClasses, tmType)) {
                    eleType = new VariableType(TYPE.CLASS, tmType);
                } else if (PRIME.isPRIME(tmType)) {
                    eleType = new VariableType(TYPE.PRIME, PRIME.prime(tmType));
                }
                varType = new VariableType(TYPE.ARRAY, eleType);
                break;

            case OTHER:
                log.warn("VARIABLE OTHER TYPE: [{}]", typeBinding);
                break;
            default:
                break;
        }
        return varType;
    }

    private boolean classDefinedInProject(Map<String, Set<Class>> prjClasses, String qualifiedClassName) {

        Set<Entry<String, Set<Class>>> entrySet = prjClasses.entrySet();
        Iterator<Entry<String, Set<Class>>> classIterator = entrySet.iterator();
        while (classIterator.hasNext()) {
            Entry<String, Set<Class>> nextClass = classIterator.next();
            Set<Class> clsValSet = nextClass.getValue();
            for (Class clsVal : clsValSet) {
                if (clsVal.getQualifiedName().contains(qualifiedClassName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
