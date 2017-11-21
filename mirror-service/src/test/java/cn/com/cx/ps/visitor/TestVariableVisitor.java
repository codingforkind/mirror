package cn.com.cx.ps.visitor;

import cn.com.cx.ps.project.MirrorProject;
import cn.com.cx.ps.utils.AstUtils;
import org.junit.Test;

public class TestVariableVisitor {
    @Test
    public void test() {
        String testFile = "/home/piggy/work/OOJavaSlicer_huaweiBug" +
                "/src/com/iseu/CASTVistitors/CASTVisitorJavaMethodsCheck.java";
        VariableVisitor variableVisitor = new VariableVisitor(testFile, new MirrorProject(testFile));
        AstUtils.getCompUnitResolveBinding(testFile).accept(variableVisitor);
    }
}
