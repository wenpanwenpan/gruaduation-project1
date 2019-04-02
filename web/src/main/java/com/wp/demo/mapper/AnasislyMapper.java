package com.wp.demo.mapper;

import com.wp.demo.bean.CommodityType;
import com.wp.demo.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnasislyMapper {

    @Select("select a.type,a.count,c.type_name from anasislytype a,commoditytype c where a.type = c.id")
    public List<CommodityType> getCommodityType();
}
