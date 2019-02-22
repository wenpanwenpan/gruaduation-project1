package com.wp.demo.controller;

import com.wp.demo.bean.Commodity;
import com.wp.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/2/22.
 * 做为购物控制器的扩展补充
 */
@Controller
public class ShoppingControllerExtend {

    @Autowired
    ProductService productService;

    /**
     * 我要出售，处理用户点击“我要出售”时的响应
     * @return
     */
    @RequestMapping(value = "/usr/sell")
    public String iWantSell(){



        return "/shopping/sell";
    }

    @RequestMapping(value = "/usr/sell/addCommodity",method = RequestMethod.POST)
    public String sell(Commodity commodity) throws Exception {

        productService.doCreate(commodity);
        System.out.println(commodity);
        return "/shopping/sell";
    }

}
