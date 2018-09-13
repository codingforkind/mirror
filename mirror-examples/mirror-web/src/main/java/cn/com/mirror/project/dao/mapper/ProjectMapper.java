package cn.com.mirror.project.dao.mapper;

import cn.com.mirror.common.PgCommonMapper;
import cn.com.mirror.project.dao.entity.Project;
import cn.com.mirror.project.dao.entity.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper extends PgCommonMapper<Project> {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    List<Project> selectByExample(ProjectExample example);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);
}