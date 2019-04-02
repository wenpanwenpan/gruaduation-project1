package com.wp.demo.mapper;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Customer;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2019/3/13.
 */
@Mapper
public interface AdminUserMapper {

    //重置用户密码,默认重置为123456
    @Update("update customer set password = '123456' where uid = #{uid}")
    public Integer resetUserPassword(@Param("uid") Integer uid);

    @Select("select * from customer")
    public Page<Customer> findAllUser();

    @Delete("delete customer where uid = #{uid}")
    public Integer doRemoveUserById(@Param("uid") Integer uid);
}
