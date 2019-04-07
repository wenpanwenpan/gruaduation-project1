package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Content;
import com.wp.demo.bean.ViewContent;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2019/4/3.
 * 处理用户评论的mapper类
 */
@Mapper
public interface UserContentMapper {

    @Insert("insert into usercontent(user_id,update_time,commodity_id,seller_id,content) " +
            " values(#{userId},#{updateTime},#{commodityId},#{sellerId},#{content})")
    public Integer doCreate(Content content);

    /*@Select("select user_id,update_time,commodity_id,seller_id,content,content_id from usercontent where seller_id = #{sellerId}")*/
    @Select("SELECT\n" +
            "\tbb.content AS content,\n" +
            "\tbb.content_id AS contentId,\n" +
            "\tbb.uname AS uname,\n" +
            "\tbb.update_time AS updateTime,\n" +
            "\tcc. NAME AS pname,\n" +
            "\tcc.pid AS pid\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tu.content_id,\n" +
            "\t\t\tu.commodity_id,\n" +
            "\t\t\tu.content,\n" +
            "\t\t\tu.update_time,\n" +
            "\t\t\tcu.uname\n" +
            "\t\tFROM\n" +
            "\t\t\tusercontent u,\n" +
            "\t\t\tcustomer cu\n" +
            "\t\tWHERE\n" +
            "\t\t\tu.user_id = cu.uid\n" +
            "\t\tAND u.seller_id = #{sellerId}\n" +
            "\t) bb,\n" +
            "\tcommodity cc\n" +
            "WHERE\n" +
            "\tbb.commodity_id = cc.pid\n" +
            "ORDER BY\n" +
            "\tcontent_id DESC")
    public Page<ViewContent> findAllContent(@Param("sellerId") Integer sellerId);

    @Delete("delete from usercontent where content_id = #{id}")
    public Integer removeContentById(@Param("id") Integer id);

    @Delete("delete from usercontent where seller_id = #{sellerId}")
    public Integer removeAllContent(@Param("sellerId") Integer sellerId);


}
