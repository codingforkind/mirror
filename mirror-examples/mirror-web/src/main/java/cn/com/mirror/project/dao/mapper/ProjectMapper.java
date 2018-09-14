package cn.com.mirror.project.dao.mapper;

import cn.com.mirror.common.PgCommonMapper;
import cn.com.mirror.project.dao.entity.Project;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

public interface ProjectMapper extends PgCommonMapper<Project> {

    @Options(keyProperty = "prjId", useGeneratedKeys = true)
    @InsertProvider(type = BaseInsertProvider.class,method = "dynamicSQL")
    int insertSelective(Project var1);
}