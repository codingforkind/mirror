package cn.com.cx.ps.mirror.visitor;

import cn.com.cx.ps.mirror.constant.NodeTypeEnum;
import cn.com.cx.ps.mirror.graph.node.ClassNode;
import cn.com.cx.ps.mirror.graph.node.MethodNode;
import cn.com.cx.ps.mirror.graph.node.Node;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import cn.com.cx.ps.mirror.java.variable.VariableType;
import cn.com.cx.ps.mirror.java.variable.VariableType.PRIME;
import cn.com.cx.ps.mirror.java.variable.VariableType.TYPE;
import cn.com.cx.ps.mirror.utils.AstUtils;
import cn.com.cx.ps.mirror.utils.UUIDUtils;
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
 * Extract all the variables in the java file.
 */
@Getter
@Setter
@Slf4j
public class VariableVisitor extends ASTVisitor {
	/**
	 * java file name
	 */
	private String file;
	/**
	 * package name of the file
	 */
	private String packageName;
	/**
	 * all classes defined in the project
	 */
	private Map<String, Set<Class>> prjClasses;

	private Set<Variable> varSet = new HashSet<>(); // all variable defined in this java file
	private Map<Integer, Set<Variable>> varInFile = new TreeMap<>();

	private Set<ClassNode> classNodeSet = new HashSet<>();
	private Set<MethodNode> methodNodeSet = new HashSet<>();

	public VariableVisitor(String file, String packageName, Map<String, Set<Class>> prjClasses) {
		this.file = file;
		this.packageName = packageName;
		this.prjClasses = prjClasses;
	}

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

			// Variable type handle AND TYPE ONLY
			variable.setVariableType(analysisVariableType(varTypeBinding.getType()));
			addVariable(AstUtils.getEndLine(node), variable);

			varSet.add(variable);
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		String id = UUIDUtils.randomUUID();
		Integer startLine = AstUtils.getEndLine(node.getName());
		Integer endLine = AstUtils.getEndLine(node);
		String className = node.getName().getIdentifier();

		ClassNode classNode = ClassNode.instance(new Node(id, NodeTypeEnum.CLASS, startLine, this.file),
				this.packageName, className, endLine, node);
		this.classNodeSet.add(classNode);
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		String methodName = node.getName().getIdentifier();
		Integer startLine = AstUtils.getEndLine(node.getName());
//		TODO 方法和类不再单独的强调其语法属性，把各种属性放到节点中；即把类cn.com.cx.ps.mirror.java.variable.Class放入Node中作为属性
//		，并且在ClassDeclarationVisitor中把ClassNode提取
		
		
		return super.visit(node);
	}
	

	private void addVariable(Integer lineNum, Variable variable) {
		if (!varInFile.containsKey(lineNum)) {
			Set<Variable> set = new HashSet<>();
			set.add(variable);
			varInFile.put(lineNum, set);
		} else {
			varInFile.get(lineNum).add(variable);
		}
	}

	/**
	 * check the qualified class name is included in this project, if not then skip
	 * it if so just initializing this class variable type into the project object.
	 */
	private VariableType analysisVariableType(ITypeBinding typeBinding) {
		VariableType varType = null;
		switch (VariableType.TYPE.judgeType(typeBinding)) {
		case PRIME:
			varType = new VariableType(TYPE.PRIME, PRIME.prime(typeBinding.getName()));
			break;

		case CLASS:
			String clsTypeQualifiedName = typeBinding.getQualifiedName();

			if (classDefinedInProject(prjClasses, clsTypeQualifiedName)) {
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
			if (classDefinedInProject(prjClasses, tmType)) {
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
