package com.wp.demo.controller;

import com.wp.demo.bean.Customer;
import com.wp.demo.service.UserRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/18.
 */
@Controller
public class RegistController {

    @Autowired
    UserRegistService userRegistService;

    /**
     * 处理用户注册请求
     * @return
     */
    @GetMapping(value = "/regist/registAccount")
    public String userRegistPre(){

        return "/regist";
    }

    @PostMapping(value = "/regist/userRegist")
    public String userRegist(Customer customer, Map map){
        System.out.println("注册信息：" + customer);
        boolean flg = userRegistService.userRegist(customer);
        //注册成功，则跳转到登录页面
        if(flg){
            map.put("msg","注册成功，请登录！");
            return "login";
        }
        map.put("msg","注册失败，请校验信息！");
        return "/regist";
    }

}
