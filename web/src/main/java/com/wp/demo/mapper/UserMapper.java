package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/2/24.
 */
@Mapper
public interface UserMapper {

    //修改一个用户信息（修改用户出售商品的集合）
    public Integer doUpdate(Customer customer);

    //用户修改基本信息
    public Integer doUpdatePersonalInfo(Customer customer);

    //通过用户id查询得到用户信息
    public Customer findCustomerById(@Param("uid") Integer uid);

    public Integer doRemoveUserById(@Param("uid") Integer uid);

}
