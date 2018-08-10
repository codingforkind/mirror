package cn.com.mirror.analysor;

import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.factory.UnitFactory;
import cn.com.mirror.repository.code.CodeRepository;
import cn.com.mirror.repository.code.LocalRepository;
import cn.com.mirror.utils.AstUtils;
import cn.com.mirror.utils.FileUtils;
import cn.com.mirror.analysor.visitor.ClassVisitor;
import cn.com.mirror.analysor.visitor.VariableVisitor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class UnitAnalysor {


    public Unit analyze() {
        UnitFactory unitFactory = new UnitFactory();
        CodeRepository codeRepository = new LocalRepository();
        return unitFactory.newUnit(codeRepository.getRepositoryUrl());
    }

}
