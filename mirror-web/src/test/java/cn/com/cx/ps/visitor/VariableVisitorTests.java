/**
 * 
 */
package cn.com.cx.ps.visitor;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.cx.mirror.web.MirrorWebApplication;
import cn.com.cx.ps.mirror.common.utils.AstUtils;
import cn.com.cx.ps.mirror.common.utils.MirrorTestProperties;
import cn.com.cx.ps.mirror.common.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.mirror.common.visitor.VariableVisitor;
import cn.com.cx.ps.mirror.java.variable.Class;
import cn.com.cx.ps.mirror.java.variable.Variable;

/**
 * @author Piggy
 *
 * @description 
 * @date 2018年2月1日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MirrorWebApplication.class)
public class VariableVisitorTests {
	
	@Autowired
	private MirrorTestProperties mirrorTestProperties;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testVariable() {
		String path = mirrorTestProperties.getFile();
		CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(path);
		log.info(path);
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(path);
		compilationUnit.accept(classDeclarationVisitor);
		Map<String, Set<Class>> classMap = new HashMap<>();
		classMap.put(path, classDeclarationVisitor.getPrjClasses());
		VariableVisitor variableVisitor = new VariableVisitor(mirrorTestProperties.getFile(), classMap);
		compilationUnit.accept(variableVisitor);
		
		variableVisitor.getVariables();
		
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
