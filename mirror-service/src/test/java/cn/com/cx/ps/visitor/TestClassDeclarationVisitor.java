package cn.com.cx.ps.visitor;

import cn.com.cx.ps.utils.AstUtils;
import org.junit.Test;

public class TestClassDeclarationVisitor {

    @Test
    public void test() {
//        String testFile = "/home/piggy/work/ps/OOJavaSlicer_huaweiBug/src/com/iseu/CASTVistitors/CASTVisitorJavaMethodsCheck.java";
        String testFile = "/home/piggy/work/ps/OOJavaSlicer_huaweiBug/src/test/GeneraticClass.java";
        AstUtils.getCompUnitResolveBinding(testFile).accept(new ClassDeclarationVisitor());
    }
}
