package com.wp.demo.controller;

import com.wp.demo.bean.Customer;
import com.wp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/2/24.
 * 处理用户的相关操作
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 点击跳转到个人中心去
     * @return
     */
    @GetMapping(value = "/user/personalcenter")
    public String toPersonalCenter(){

        return "/userPage/personalCenter";
    }

    /**
     * 点击跳转到关于我们界面
     * @return
     */
    @GetMapping(value = "/user/aboutUs")
    public String toAboutUs(HttpSession session){

        if(session.getAttribute("adminLoginUser") != null){
            return "/adminPage/aboutUs";
        }
        return "/userPage/aboutUs";
    }

    /**
     * 修改个人信息,跳转到修改页面进行内容回显
     * @return
     */
    @RequestMapping(value = "/user/modifyPersonalInfoPre",method = RequestMethod.GET)
    public String modifyPersonalInfoPre(Model model, HttpSession session){

        Customer customer = (Customer) session.getAttribute("customer");
        //如果该用户已经登录
        if(customer != null){
            Customer customerById = userService.findCustomerById(customer.getUid());
            model.addAttribute("customer",customerById);
            //返回进行页面回显
            return "/userPage/personalInfo";
        }else{
            model.addAttribute("msg","请先登录");
            //未登录则返回登录界面提示登录
            return "login";
        }
    }


    /**
     * 确认修改个人信息
     * @return
     */
    @RequestMapping(value = "/user/modifyPersonalInfo",method = RequestMethod.POST)
    public String modifyPersonalInfo(Customer customer,Model model,HttpSession session){

        Customer customer1 = (Customer) session.getAttribute("customer");

        if(customer1 != null){
            //从属性域中取出用户信息并且取出用户uid并设置到customer对象中
            customer.setUid(customer1.getUid());

            //向数据库修改信息
            boolean flag = userService.doUpdatePersonalInfo(customer);

            Customer customerById = userService.findCustomerById(customer.getUid());
            model.addAttribute("customer",customerById);
            model.addAttribute("PersonalInfo","修改信息成功！");
            return "/userPage/personalInfo";
        }else {
            model.addAttribute("msg","请先登录");
            //未登录则返回登录界面提示登录
            return "login";
        }

    }
    /**
     * 点击跳转到管理我出售的商品信息
     * @return
     */
    //@GetMapping(value = "/user/manageMyCommodities")
    public String manageMyCommodities(){

        return "";
    }



}
