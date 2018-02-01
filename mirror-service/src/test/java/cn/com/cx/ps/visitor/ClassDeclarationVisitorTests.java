/**
 * 
 */
package cn.com.cx.ps.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cx.ps.mirror.common.utils.AstUtils;
import cn.com.cx.ps.mirror.common.visitor.ClassDeclarationVisitor;

/**
 * @author Piggy
 *
 * @description
 * @date 2018年1月28日
 */
public class ClassDeclarationVisitorTests {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		String path = "D:\\work\\test\\mirror\\mirror-service\\src\\main\\java\\cn\\com\\cx\\ps\\mirror\\common\\visitor\\ClassDeclarationVisitor.java";
		CompilationUnit compilationUnit = AstUtils.getCompUnitResolveBinding(path);
		System.out.println(path);
		log.info(path);
		ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor(path);
		compilationUnit.accept(classDeclarationVisitor);
		classDeclarationVisitor.getPrjClasses().forEach(cls -> {
			log.info("CLASS: {}, {}, {}", cls.getQualifiedName(), cls.getName(), cls.getPackageName());
		});
	}

}
