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

    //处理登录请求
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
}
