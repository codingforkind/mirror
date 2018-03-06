/**
 * 
 */
package cn.com.cx.ps.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.cx.ps.mirror.MirrorServiceApplication;
import cn.com.cx.ps.mirror.common.utils.AstUtils;
import cn.com.cx.ps.mirror.common.utils.MirrorTestProperties;
import cn.com.cx.ps.mirror.common.visitor.ClassDeclarationVisitor;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年1月28日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MirrorServiceApplication.class)
public class ClassDeclarationVisitorTests {
	@Autowired
	private MirrorTestProperties mirrorTestProperties;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		String path = mirrorTestProperties.getFile();
		CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(path);
		log.info(path);
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(path);
		compilationUnit.accept(classDeclarationVisitor);
		
		classDeclarationVisitor.getPrjClasses().forEach(cls -> {
			log.info("CLASS: {}, {}, {}", cls.getQualifiedName(), cls.getName(), cls.getPackageName());
		});
	}

}
