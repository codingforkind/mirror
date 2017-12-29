package cn.com.cx.ps.visitor;

import cn.com.cx.ps.mirror.project.MirrorProject;
import cn.com.cx.ps.mirror.project.ProjectProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
public class TestVariableVisitor {
    @Autowired
    private ProjectProperty projectProperty;

    @Test
    public void test() throws  Exception{
        MirrorProject mirrorProject = new MirrorProject(new File(projectProperty.getDir()));
//        AstUtils.getCompUnitResolveBinding(testFile).accept(variableVisitor);
    }
}
