package com.wp.demo.mapper;

import com.wp.demo.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2019/2/24.
 */
@Mapper
public interface UserLoginMapper {

    //通过账号和密码从数据库获取是否有该用户信息
    public Customer getUserLoginByAccount(@Param("account") String account, @Param("password") String password);
}
