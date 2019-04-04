package com.wp.demo.service;

import com.wp.demo.bean.Customer;
import com.wp.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/2/24.
 * 用户相关操作服务层，比如修改密码，上传商品等操作
 */
@Service
public interface IUserService<K,T> {

    public boolean doUpdate(T bean);

    public Customer findCustomerById(K key);

    public boolean doUpdatePersonalInfo(T bean);

    public boolean doRemoveUserById(K key);

}
