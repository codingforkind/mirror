package cn.com.mirror.project.dao.mapper;

import cn.com.mirror.common.PgCommonMapper;
import cn.com.mirror.project.dao.entity.Archive;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

public interface ArchiveMapper extends PgCommonMapper<Archive> {

    @Override
    @Options(keyProperty = "achvId", useGeneratedKeys = true)
    @InsertProvider(type = BaseInsertProvider.class,method = "dynamicSQL")
    int insertSelective(Archive archive);
}