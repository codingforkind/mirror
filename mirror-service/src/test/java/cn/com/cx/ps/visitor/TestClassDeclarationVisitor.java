package cn.com.cx.ps.visitor;

import cn.com.cx.ps.mirror.visitor.ClassDeclarationVisitor;
import cn.com.cx.ps.utils.AstUtils;
import org.junit.Test;

public class TestClassDeclarationVisitor {

    @Test
    public void test() {
        String testFile = "/home/piggy/work/OOJavaSlicer_huaweiBug/src/com/iseu/CASTVistitors/CASTVisitorJavaMethodsCheck.java";
        AstUtils.getCompUnitResolveBinding(testFile).accept(new ClassDeclarationVisitor());
    }
}
