package com.wp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.CommodityType;
import com.wp.demo.bean.Customer;
import com.wp.demo.service.ProductAndUserService;
import com.wp.demo.service.ProductService;
import com.wp.demo.service.UserService;
import com.wp.demo.utils.IPTimeStamp;
import com.wp.demo.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/2/22.
 * 做为购物控制器的扩展补充
 */
@Controller
public class ShoppingControllerExtend {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductAndUserService productAndUserService;

    //用于生成上传的商品照片名
    IPTimeStamp ipTimeStamp = new IPTimeStamp("192.168.1.1");

    //用于后台打印日志
    private static final Logger log = LoggerFactory.getLogger(ShoppingControllerExtend.class);

    /**
     * 获取登录的顾客信息
     * @return
     */
    public Customer getCustomer(HttpSession session){
        Customer customer = (Customer)session.getAttribute("customer");
        return customer;
    }

    /**
     * 我要出售，处理用户点击“我要出售”时的响应
     * @return
     */
    @RequestMapping(value = "/usr/sell")
    public String iWantSell(Model model){

        //从商品表中查询出所有类型,用于显示选择框
        List<CommodityType> allCommodityType = productService.findAllCommodityType();
        model.addAttribute("allCommodityType",allCommodityType);

        return "/shopping/sell";
    }

    /**
     * 此处需要使用事务控制
     * 新增加一件要出售的二手商品，其中包含了商品的图片上传
     * @param file
     * @param commodity
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/usr/sell/addCommodity")
    public String sell(Commodity commodity, @RequestParam("file") MultipartFile file, HttpSession session,Model model) throws Exception {

        String fileName = null;
        //如果有照片上传
        if(!file.isEmpty() && file.getSize() > 0){
            log.info("[文件类型] - [{}]", file.getContentType());
            log.info("[文件名称] - [{}]", file.getOriginalFilename());
            log.info("[文件大小] - [{}]", file.getSize());
            //TODO 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx
            //进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
            fileName = ipTimeStamp.getIPTimeRand() + ".jpg";     //重新生成图片名称，保证不重复
            file.transferTo(new File("E:\\IDEAWorkSpace\\GraduationProject\\gruaduation-project1\\web\\src\\main\\resources\\static\\images\\" + fileName));

        }
        //取得session域中的用户信息
        Customer customer = (Customer) session.getAttribute("customer");
        //取得用户所出售的所有商品信息
        String commodities = customer.getCommodities();

        //设置要保存到数据库里面的照片名称
        commodity.setPhoto(fileName);

        //设置用户商品上传的日期
        String date = UserUtils.dateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        commodity.setDate(date);

        //设置该商品所属的用户
        commodity.setAuthorId(customer.getUid() + "");
        System.out.println("要添加的商品：" + commodity);
        //向数据库中保存该商品，必须要先保存该商品，才能在数据库中获取该商品的自增id值，然后保存到用户的商品集合
        productService.doCreate(commodity);

        //如果该用户第一次上传出售商品
        if(commodities == null || "".equals(commodities)){
            //将该用户上传的商品的id保存到对应属性中
            customer.setCommodities(commodity.getPid() + "");
        }else {
            commodities = commodities + "_" + commodity.getPid();
            customer.setCommodities(commodities);
        }

        System.out.println("customer:  " + customer);
        //将用户信息保存回数据库
        userService.doUpdate(customer);
        productAndUserService.addCommodity(customer,commodity);

        //从商品表中查询出所有类型,用于显示选择框
        List<CommodityType> allCommodityType = productService.findAllCommodityType();
        model.addAttribute("allCommodityType",allCommodityType);
        model.addAttribute("msg","发布成功！");

        return "/shopping/sell";
    }

    /**
     * 管理用户自己出售的商品页面
     * @return
     */
    @GetMapping(value = "/user/manageMyCommodities")
    public String manageMySell(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "6") int pageSize,
                               Model model,
                               HttpSession session){
        PageHelper.startPage(pageNo, pageSize,true);
        Customer customer = (Customer)session.getAttribute("customer");

        if (customer != null){
            Page<Commodity> commodities = productService.manageMySell(customer.getUid());
            //用于分页条显示
            if(commodities != null){
                PageInfo<Commodity> page = new PageInfo<>(commodities,5);
                model.addAttribute("pageInfo",page);
                System.out.println("总页数：" + page.getPages() + "前一页：" + page.getPrePage() + "后一页： " +
                        page.getNextPage());
            }else {
                model.addAttribute("pageInfo",new PageInfo<>());
            }

            model.addAttribute("customer",customer);
        }

        return "/shopping/manageMySell";
    }

    /**
     * 删除一件我出售的商品,此处要使用事务！！！！
     * @param pid
     * @return
     */
    @GetMapping(value = "/usr/ajax/deleteMyCommodity/{pid}")
    public String deleteMyCommodity(@PathVariable("pid") String pid,HttpSession session) throws Exception {

        Customer customer = getCustomer(session);
        boolean isUsersCommodity = UserUtils.isUsersCommodity(pid + "", customer);
        //检测用户是否是删除的自己上传的商品
        if(isUsersCommodity){
           Customer customer1 = UserUtils.ModifyUsersCommoditiesUtils(customer, pid, productService,userService);
           session.setAttribute("customer",customer1);
       }

        return "redirect:/user/manageMyCommodities";
    }

    /**
     * 修改出售的商品信息
     * @return
     */
    @GetMapping(value = "/user/modifyMySellCommodityPre/{pid}")
    public String modityCommodityInfoPre(@PathVariable("pid") Integer pid,Model model,HttpSession session) throws Exception {

        //此处需要验证路径变量中的值是否是该用户拥有的商品
        Customer customer = (Customer)session.getAttribute("customer");
        boolean isUsersCommodity = UserUtils.isUsersCommodity(pid + "", customer);
        if (isUsersCommodity){
            Commodity commodity = productService.findById(pid);
            List<CommodityType> allCommodityType = productService.findAllCommodityType();
            model.addAttribute("commodity",commodity);
            model.addAttribute("allCommodityType",allCommodityType);
        }

        return "/userPage/modifyMySellCommodity";
    }

    /**
     * 用户修改他出售的商品信息
     * @param commodity
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/modifyMySellCommodity")
    public String modityCommodityInfo(Commodity commodity) throws Exception {

        //更新用户上传商品的时间
        String dateFormat = UserUtils.dateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        commodity.setDate(dateFormat);
        productService.doUpdate(commodity);

        return "redirect:/user/manageMyCommodities";
    }
}
