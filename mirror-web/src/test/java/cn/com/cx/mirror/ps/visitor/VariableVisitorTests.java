/**
 * 
 */
package cn.com.cx.mirror.ps.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.cx.mirror.web.MirrorWebApplication;
import cn.com.cx.mirror.web.config.property.MirrorTestProperties;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;
import cn.com.cx.ps.mirror.utils.AstUtils;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MirrorWebApplication.class)
@Slf4j
public class VariableVisitorTests {
	
	@Autowired
	private MirrorTestProperties mirrorTestProperties;
	
	@Test
	public void testVariable() {
		String path = mirrorTestProperties.getFile();
		CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(path);
		PackageVisitor packageVisitor = new PackageVisitor();
		compilationUnit.accept(packageVisitor);
		log.info(path);
		
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(path);
		compilationUnit.accept(classDeclarationVisitor);
		Map<String, Set<Class>> classMap = new HashMap<>();
		classMap.put(path, classDeclarationVisitor.getPrjClasses());
		VariableVisitor variableVisitor = new VariableVisitor(mirrorTestProperties.getFile(),
				packageVisitor.getPackageName(), classMap);
		compilationUnit.accept(variableVisitor);
		
//		log.info("Variables: {}", variableVisitor.getVariables());
		Map<Integer, Set<Variable>> varInFile = variableVisitor.getVarInFile();
		Set<Entry<Integer, Set<Variable>>> entrySet = varInFile.entrySet();
		Iterator<Entry<Integer, Set<Variable>>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Set<Variable>> next = iterator.next();
			List<String> valNames = new ArrayList<>();
			for(Variable variable : next.getValue()) {
				valNames.add(variable.getName());
			}
			log.info("Line: {}, VARS: {}", next.getKey(), valNames);
		}
	}

}
