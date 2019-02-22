package com.wp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/2/14.
 */
@Controller
public class LogoutController {

    /**
     * 用户注销登录
     * @return
     */
    @RequestMapping(value = "/usr/ajax/userLogout" ,method = RequestMethod.GET)
    public String userLogout(HttpSession session){

        if(session != null && session.getAttribute("loginUser") != null){
            session.removeAttribute("loginUser");
        }

        //注销登录后重新定向到二手商品界面
        return "redirect:/shopping/goshopping";
    }

    /**
     * 管理员注销登录
     * @param session
     */
    @RequestMapping(value = "/usr/ajax/adminLogout" ,method = RequestMethod.GET)
    public void adminLogout(HttpSession session){

        if(session != null && session.getAttribute("adminLoginUser") != null){
            session.removeAttribute("adminLoginUser");
        }
    }


}
