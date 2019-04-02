package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.CommodityType;
import org.apache.ibatis.annotations.*;

import java.util.List;


//指定这是一个操作数据库的mapper
@Mapper
public interface ProductMapper {

    //查询出所有的商品，并且保证该商品的数量不等于0
    @Select("select * from commodity where count != 0")
    public Page<Commodity> getAllCommodity();


    @Select("select * from commodity where pid = #{pid}")
    public Commodity findById(Integer id);

    //增加一件二手商品  需返回自增主键（使用返回的商品的主键然后将该值放入对应的用户的商品字段中）
    @Insert("insert into commodity(name,price,note,photo,count,author_id,date,tid) values(#{name},#{price},#{note},#{photo},#{count},#{authorId},#{date},#{tid})")
    @Options(useGeneratedKeys = true, keyProperty = "pid", keyColumn = "pid")
    public int doCreate(Commodity commodity);


    @Select("select id,type_name from commoditytype")
    public List<CommodityType> findAllCommodityType();
   /* @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    //将插入的数据要进行回显
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Commodity Commodity);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Commodity Commodity);*/

   //从搜索框中查找二手商品
    @Select("select * from commodity where name like concat(concat('%',#{key}),'%') or " +
            " note like concat(concat('%',#{key}),'%') or price like concat(concat('%',#{key}),'%') or author_id like concat(concat('%',#{key}),'%')")
   public  Page<Commodity> seachCommodity(@Param("key") String key);

    //通过商品的pid集合查找出用户所出售的所有商品
    public Page<Commodity> findMySellByPids(@Param("pids") List<Integer> pids);

    public Integer removeByPid(@Param("pid") Integer pid);

    public Integer doUpdate(Commodity commodity);


}
