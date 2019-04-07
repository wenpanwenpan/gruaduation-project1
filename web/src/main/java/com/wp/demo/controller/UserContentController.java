package com.wp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Content;
import com.wp.demo.bean.Customer;
import com.wp.demo.bean.ViewContent;
import com.wp.demo.service.ProductService;
import com.wp.demo.service.UserContentService;
import com.wp.demo.service.UserService;
import com.wp.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/4/3.
 * 用户留言控制器：
 * 主要用于处理用户的留言和卖家的回复留言操作
 */
@Controller
public class UserContentController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    UserContentService userContentService;

    /**
     * 获取登录的顾客信息
     * @return
     */
    public Customer getCustomer(HttpSession session){
        Customer customer = (Customer)session.getAttribute("customer");
        return customer;
    }

    /**
     * 处理用户留言操作pre
     * @return
     */
    @GetMapping(value = "/user/message/{pid}")
    public String messagePre(@PathVariable("pid") Integer pid, Model model) throws Exception {

        Commodity commodity = productService.findById(pid);
        if(commodity != null){
            model.addAttribute("commodity",commodity);
            return "/userPage/message";
        }

        return "redirect:/shopping/goshoppingbypage";
    }

    /**
     * 处理用户留言请求
     * @return
     */
    @PostMapping(value = "/user/content")
    public String dealContent(Content content,HttpSession session,Model model) throws Exception {

        Customer customer = getCustomer(session);
        Commodity commodity = productService.findById(content.getCommodityId());
        content.setUserId(customer.getUid());
        content.setUpdateTime(UserUtils.dateFormat("yyyy-MM-dd"));
        content.setSellerId(Integer.parseInt(commodity.getAuthorId()));
        boolean flag = userContentService.doCreate(content);

        if(flag){
            model.addAttribute("msg","恭喜您，评论成功！");
        }else {
            model.addAttribute("msg","评论失败，稍后再试！");
        }
        model.addAttribute("commodity",commodity);
        //留言成功返回结果
        return "/userPage/message";
    }

    /**
     *出售者查看自己所有发布商品的评论
     * @return
     */
    @GetMapping(value = "/user/viewContent")
    public String viewContentPre(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 HttpSession session,
                                 Model model) throws Exception {
        PageHelper.startPage(pageNo, pageSize,true);
        Customer customer = getCustomer(session);
        Page<ViewContent> contents = userContentService.findAllById(customer.getUid());
        PageInfo<ViewContent> page = new PageInfo<>(contents,5);
        model.addAttribute("pageInfo",page);

        return "/userPage/allContent";
    }

    /**
     * 删除一条顾客的评论
     * @return
     */
    @GetMapping(value = "/user/removecontent/{cid}")
    public String removeContentById(@PathVariable("cid") Integer cid) throws Exception {

        boolean flag = userContentService.doRemove(cid);

        return "redirect:/user/viewContent";
    }

    @ResponseBody
    @GetMapping(value = "/user/viewcommoditydetail/{pid}")
    public Commodity viewCommodityDetail(@PathVariable("pid") Integer pid) throws Exception {

        Commodity commodity = productService.findById(pid);
        return commodity;
    }

    /**
     * 控控留言
     * @param session
     * @return
     */
    @GetMapping(value = "/user/removeallcontent")
    public String removeAllContent(HttpSession session){

        Customer customer = getCustomer(session);
        if(customer != null){
            Integer uid = customer.getUid();
            userContentService.removeAllContent(uid);
        }
        return "redirect:/user/viewContent";
    }

}
