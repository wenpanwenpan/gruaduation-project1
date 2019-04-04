package com.wp.demo.service;

import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Customer;
import com.wp.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/2/24.
 * 用户相关操作服务层，比如修改密码，上传商品等操作
 */
@Service
public class UserService implements IUserService<Integer,Customer> {

    @Autowired
    UserMapper userMapper;

    public boolean doUpdate(Customer customer){

        boolean flag = true;
        Integer integer = userMapper.doUpdate(customer);

        return flag;
    }

    /**
     * 通过id查找到员工信息
     * @param id
     * @return
     */
    @Override
    public Customer findCustomerById(Integer id){
        Customer customer = userMapper.findCustomerById(id);
        return customer;
    }

    public boolean doUpdatePersonalInfo(Customer customer){

        boolean flag = false;
        Integer num = userMapper.doUpdatePersonalInfo(customer);
        if(num > 0 ){
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean doRemoveUserById(Integer uid) {

        Integer integer = userMapper.doRemoveUserById(uid);
        if(integer > 0){
            return true;
        }
        return false;
    }

}
