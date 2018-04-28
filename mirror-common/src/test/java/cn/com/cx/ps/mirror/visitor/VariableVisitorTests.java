package cn.com.cx.ps.mirror.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import cn.com.cx.ps.mirror.utils.AstUtils;
import cn.com.cx.ps.mirror.utils.FileUtils;
import cn.com.cx.ps.mirror.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.mirror.visitor.PackageVisitor;
import cn.com.cx.ps.mirror.visitor.VariableVisitor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年2月1日
 */
@Slf4j
public class VariableVisitorTests {
	private static final String FILE_PATH = "D:/work/test/mirror/mirror-service/src/main/java/cn/com/cx/ps/mirror/common/visitor/ClassDeclarationVisitor.java";
	private static final String PRJ_PATH = "D:/work/test/mirror";

	@Test
	public void testPrjVariables() {
		Set<String> prjJavaFileSet = FileUtils.prjJavaFileSet(PRJ_PATH);
		int count = 0;
		for (String javaFile : prjJavaFileSet) {
			count++;
			log.info(javaFile);
			testjavaFile(javaFile);
		}
		log.info("Java file count: [{}]", count);
	}

	private void testjavaFile(String filePath) {
		CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(filePath);
		PackageVisitor packageVisitor = new PackageVisitor();
		compilationUnit.accept(packageVisitor);

		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(filePath);
		compilationUnit.accept(classDeclarationVisitor);
		Map<String, Set<Class>> classMap = new HashMap<>();
		classMap.put(filePath, classDeclarationVisitor.getPrjClasses());
		VariableVisitor variableVisitor = new VariableVisitor(filePath, packageVisitor.getPackageName(), classMap);
		compilationUnit.accept(variableVisitor);

		// log.info("Variables: {}", variableVisitor.getVariables());
		Map<Integer, Set<Variable>> varInFile = variableVisitor.getVarInFile();
		Set<Entry<Integer, Set<Variable>>> entrySet = varInFile.entrySet();
		Iterator<Entry<Integer, Set<Variable>>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Set<Variable>> next = iterator.next();
			List<String> valNames = new ArrayList<>();
			for (Variable variable : next.getValue()) {
				valNames.add(variable.getName());
			}
			log.info("Line: {}, VARS: {}", next.getKey(), valNames);
		}
	}

	@Test
	public void testVariable() {
		log.info(FILE_PATH);
		testjavaFile(FILE_PATH);
	}
	
	
	/**
	 * assert expression1 : expression2; 
	 * expression1为false则抛出异常并执行expression2。
	 * expression1为true则不抛出异常，expression2不执行。
	 */
	@Test
	public void test1() {
		assert 1 == 1:"RIGHT"; 
		assert 1 != 1:"WRONG";
	}

}
