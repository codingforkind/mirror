package cn.com.cx.ps.mirror.dao.mapper;

import cn.com.cx.ps.mirror.dao.entity.CustomerTbl;
import cn.com.cx.ps.mirror.dao.entity.CustomerTblExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerTblMapper extends CommonMapper<CustomerTbl> {
    int countByExample(CustomerTblExample example);

    int deleteByExample(CustomerTblExample example);

    List<CustomerTbl> selectByExample(CustomerTblExample example);

    int updateByExampleSelective(@Param("record") CustomerTbl record, @Param("example") CustomerTblExample example);

    int updateByExample(@Param("record") CustomerTbl record, @Param("example") CustomerTblExample example);
}