package com.wp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/20.
 */
@Controller
public class LoginController {

    /**
     * 处理登录请求,默认为顾客登录
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/usr/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功，跳转到主页面,为了防止表单重复提交我们可以重定向到主页，在MyMvcConfig.java中配置
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登录失败，返回登录页，给出提示信息
            map.put("msg","用户名密码错误！");
            return "login";
        }

    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/usr/adminLogin",method = RequestMethod.POST)
    public String adminLogin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "wenpan".equals(password)){
            //登录成功，跳转到主页面,为了防止表单重复提交我们可以重定向到主页，在MyMvcConfig.java中配置
            session.setAttribute("adminLoginUser",username);

            //管理员登录成功后跳转到管理员操作的界面
            return "/adminPage/myshopping";
        }else {
            //登录失败，返回登录页，给出提示信息
            map.put("msg","用户名密码错误！");
            return "adminLogin";
        }
    }

    //切换到管理员登录
    @RequestMapping(value = "/usr/switchToAdmin")
    public String switchToAdmin(){

        return "adminLogin";
    }

    //由管理员切换到普通用户登录
    @RequestMapping(value = "/usr/switchToUser")
    public String switchToUser(){

        return "login";
    }

}
