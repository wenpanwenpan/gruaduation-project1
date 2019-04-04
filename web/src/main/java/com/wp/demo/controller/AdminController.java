package com.wp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.CommodityType;
import com.wp.demo.bean.Customer;
import com.wp.demo.service.AdminProductService;
import com.wp.demo.service.ProductAndUserService;
import com.wp.demo.service.ProductService;
import com.wp.demo.service.UserService;
import com.wp.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 * 管理员控制器，用于响应管理员的管理请求
 */
@Controller
public class AdminController {

    @Autowired
    AdminProductService adminProductService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductAndUserService productAndUserService;

    /**
     * 管理用户的商品
     * @return
     */
    @GetMapping(value = "/admin/manageuser/manageUserCommoditiesPre")
    public String manageUserCommoditiesPre(@RequestParam(defaultValue = "1") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           Model model) throws Exception {
        PageHelper.startPage(pageNo, pageSize,true);
        //缓存所有的用户，此处最适合用redis缓存来做
        HashMap<String,Customer> allCustomer = new HashMap<>();
        Page<Commodity> allCommodity = adminProductService.findAll();

        //根据商品的id查询出商品的所属用户
        for (int i = 0; i < allCommodity.size(); i++) {
            Customer customer = userService.findCustomerById(Integer.parseInt(allCommodity.get(i).getAuthorId()));
            //将用户名缓存到map集合中
            allCustomer.put(customer.getUid() + "",customer);
        }

        PageInfo<Commodity> page = new PageInfo<>(allCommodity,5);
        model.addAttribute("pageInfo",page);
        model.addAttribute("allCustomer",allCustomer);

        return "/adminPage/manageUserCommodities";
    }

    /**
     * 管理员删除员工的一件商品，此处要用事务操作
     * @param pid
     * @param uid
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/admin/manageuser/removeUserCommodityById")
    public String removeUserCommodityById(@RequestParam("pid") Integer pid,
                                          @RequestParam("uid") Integer uid
                                         ) throws Exception {

        //在用户的数据库表customer中commodities字段中，删除对应的商品id值
        Customer customer = userService.findCustomerById(uid);
        boolean flag = productAndUserService.removeUsersCommodityById(customer, pid);
        if(!flag){
            return "false";
        }
        return "true";
    }

    /**
     * 管理员在管理用户界面查看用户信息
     * @return
     */
    @GetMapping(value = "/admin/manageuser/viewUserInfo/{uid}")
    public String viewUserInfo(@PathVariable("uid") Integer uid,Model model,HttpSession session){

        Customer customer = userService.findCustomerById(uid);
        model.addAttribute("customer",customer);
        if(session.getAttribute("adminLoginUser") != null){
            return "/adminPage/userInfo";
        }

        return "/userPage/userInfo";
    }

    /**
     * 管理员重置用户密码
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/admin/manageuser/resetUserPassword")
    public String resetUserPassword(@RequestParam("uid") Integer uid){
        boolean b = adminProductService.resetUserPassword(uid);
        if(b){
            return "true";
        }
        return "";
    }

    /**
     * 管理员查看所有的顾客信息
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/manageUser/viewAllUser")
    public String adminViewAllUser(@RequestParam(defaultValue = "1") int pageNo,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   Model model){
        PageHelper.startPage(pageNo, pageSize,true);
        Page<Customer> customers = adminProductService.adminViewAllUser();
        PageInfo<Customer> page = new PageInfo<>(customers,5);
        model.addAttribute("pageInfo",page);

        return "/adminPage/manageAllUsers";
    }

    /**
     * 管理员删除一个用户以及用户所有上传的商品  需要使用事务管理
     * @param uid
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/admin/manageUser/removeUser")
    public String adminRemoveUser(@RequestParam("uid") Integer uid,HttpSession session){

        Customer customerById = userService.findCustomerById(uid);
        if(productAndUserService.removeUserAndCommodities(customerById)){
            //session域中去除掉该用户的信息
            if(session.getAttribute("customer") != null){
                session.removeAttribute("customer");
                session.removeAttribute("loginUser");
            }
        }
        System.out.println("执行注销用户成功！！！");
        return "true";
    }

    /**
     * 管理员查看一个用户所上传的所有商品
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/manageUser/viewUserSells/{uid}")
    public String viewUserSells(@PathVariable("uid") Integer uid, Model model,HttpSession session){

        PageHelper.startPage(1, 6,true);
        Customer customer = userService.findCustomerById(uid);
        Page<Commodity> commodities = productService.manageMySell(uid);
        if(commodities != null){
            //用于分页条显示
            PageInfo<Commodity> page = new PageInfo<>(commodities,5);
            System.out.println("总页数：" + page.getPages());
            model.addAttribute("pageInfo",page);
        }else{      //没有出售过商品
            model.addAttribute("pageInfo",new PageInfo<>());
        }

        model.addAttribute("customer",customer);

        if(session.getAttribute("adminLoginUser") != null){
            return "/adminPage/manageMySell";
        }

        return "/shopping/manageMySell";
    }

    /**
     * 处理管理员登录时搜索商品
     * @param key
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/amin/ajax/seachCommodity")
    public String seachCommodity(@RequestParam(value = "key") String key){
        Page<Commodity> commodities = productService.seachCommodity(key);
        if(commodities != null && commodities.size() != 0 ){
            return "true";
        }
        return "false";
    }

    /**
     * 管理员登录后搜素到商品后进行跳转
     * @return
     */
    @GetMapping(value = "/admin/ajax/seachCommodity/{key}")
    public String seachCommodityJump(@PathVariable(value = "key") String key,
                                     @RequestParam(defaultValue = "1") int pageNo,
                                     @RequestParam(defaultValue = "6") int pageSize,
                                     Model model){

        PageHelper.startPage(pageNo, pageSize);
        Page<Commodity> commodities = productService.seachCommodity(key);
        PageInfo<Commodity> page = new PageInfo<>(commodities,5);
        model.addAttribute("pageInfo",page);

        return "/adminPage/myshopping";
    }

    /**
     * 跳转到电话查询页面
     * @return
     */
    @GetMapping(value = "/admin/telHomePre")
    public String queryTelPre(){

        System.out.println("收到电话查询请求！！！");
        return "/adminPage/telHome";
    }

    /**
     * 执行归属地查询操作
     * @return
     */
    public String doQueryTel(@RequestParam("tel") String tel){


        return "false";
    }

    @GetMapping(value = "/admin/analysismap")
    public String viewMap(){

        return "/adminPage/analysisMap";
    }
}


