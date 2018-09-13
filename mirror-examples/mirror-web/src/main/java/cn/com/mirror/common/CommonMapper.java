package cn.com.mirror.common;

import tk.mybatis.mapper.additional.update.force.UpdateByPrimaryKeySelectiveForceMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface CommonMapper<T> extends BaseMapper<T>,
        ExampleMapper<T>,
        InsertListMapper<T>,
        UpdateByPrimaryKeySelectiveForceMapper<T>,
        Marker {
}
