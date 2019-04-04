package com.wp.demo.service;

import com.wp.demo.bean.Admin;
import com.wp.demo.mapper.AdminLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/3/18.
 */
@Service
public class AdminLoginService implements ILoginService<Admin> {

    @Autowired
    AdminLoginMapper adminLoginMapper;

    @Override
    public Admin getUserLoginByAccount(String account, String password) {

        Admin admin = adminLoginMapper.getUserLoginByAccount(account, password);
        System.out.println("管理员信息：" + admin);

        return admin;
    }
}
