package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;


//指定这是一个操作数据库的mapper
@Mapper
public interface ProductMapper {

    //@Select("select * from department where id=#{id}")
    //public Department getDeptById(Integer id);

    @Select("select * from commodity")
    public Page<Commodity> getAllCommodity();


    @Select("select * from commodity where pid = #{pid}")
    public Commodity findById(Integer id);

    //增加一件二手商品
    @Insert("insert into commodity(name,price,note,photo,count) values(#{name},#{price},#{note},#{photo},#{count})")
    public int doCreate(Commodity commodity);


   /* @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    //将插入的数据要进行回显
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Commodity Commodity);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Commodity Commodity);*/
}
