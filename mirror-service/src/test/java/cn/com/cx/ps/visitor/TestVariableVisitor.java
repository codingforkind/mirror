package cn.com.cx.ps.visitor;

import cn.com.cx.ps.project.MirrorProject;
import cn.com.cx.ps.util.AstUtils;
import org.junit.Test;

public class TestVariableVisitor {
    @Test
    public void test() {
//        String testFile = "/home/piggy/work/ps/OOJavaSlicer_huaweiBug/src/com/iseu/CASTVistitors/CASTVisitorJavaMethodsCheck.java";
        String testFile = "/home/piggy/work/ps/OOJavaSlicer_huaweiBug/src/test/GeneraticClass.java";
        VariableVisitor variableVisitor = new VariableVisitor(testFile, new MirrorProject(testFile));
        AstUtils.getCompUnitResolveBinding(testFile).accept(variableVisitor);
    }
}
