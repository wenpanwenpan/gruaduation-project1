package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 * 管理员管理用户出售的商品的mapper类
 */
@Mapper
public interface AdminProductMapper {

    @Select("select name,price,pid,note,count,photo,tid,author_id from commodity")
    public Page<Commodity> findAllCommodity();

    //通过商品的id集合删除商品
    public Integer removeUsersAllCommodity(@Param("pids") List<Integer> pids);

    public Integer removeByPid(@Param("pid") Integer pid);

    @Select("select * from customer where uname like concat(concat('%',#{keyword}),'%') or " +
            " tel like concat(concat('%',#{keyword}),'%') or address like concat(concat('%',#{keyword}),'%') or zip_code like concat(concat('%',#{keyword}),'%')")
    public Page<Customer> findUserByKeyWord(String keyword);
}
