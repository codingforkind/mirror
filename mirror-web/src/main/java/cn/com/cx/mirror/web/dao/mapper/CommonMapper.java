package cn.com.cx.mirror.web.dao.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface CommonMapper<T> extends BaseMapper<T>, ConditionMapper<T>, InsertListMapper<T>, Marker {
}
