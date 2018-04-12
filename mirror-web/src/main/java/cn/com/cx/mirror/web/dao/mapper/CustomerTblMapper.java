package cn.com.cx.mirror.web.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cx.mirror.web.dao.entity.CustomerTbl;
import cn.com.cx.mirror.web.dao.entity.CustomerTblExample;

public interface CustomerTblMapper extends CommonMapper<CustomerTbl> {
    int countByExample(CustomerTblExample example);

    int deleteByExample(CustomerTblExample example);

    List<CustomerTbl> selectByExample(CustomerTblExample example);

    int updateByExampleSelective(@Param("record") CustomerTbl record, @Param("example") CustomerTblExample example);

    int updateByExample(@Param("record") CustomerTbl record, @Param("example") CustomerTblExample example);
}