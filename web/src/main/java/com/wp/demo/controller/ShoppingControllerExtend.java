package com.wp.demo.controller;

import com.wp.demo.bean.Commodity;
import com.wp.demo.service.ProductService;
import com.wp.demo.utils.IPTimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/2/22.
 * 做为购物控制器的扩展补充
 */
@Controller
public class ShoppingControllerExtend {

    @Autowired
    ProductService productService;
    //用于生成上传的商品照片名
    IPTimeStamp ipTimeStamp = new IPTimeStamp("192.168.1.1");

    //用于后台打印日志
    private static final Logger log = LoggerFactory.getLogger(ShoppingControllerExtend.class);

    /**
     * 我要出售，处理用户点击“我要出售”时的响应
     * @return
     */
    @RequestMapping(value = "/usr/sell")
    public String iWantSell(){
        return "/shopping/sell";
    }

    /**
     * 新增加一件要出售的二手商品，其中包含了商品的图片上传
     * @param file
     * @param commodity
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/usr/sell/addCommodity")
    public String sell(Commodity commodity,@RequestParam("file") MultipartFile file) throws Exception {

        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
        //TODO 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx
        //进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
        String fileName = ipTimeStamp.getIPTimeRand() + ".jpg";     //重新生成图片名称，保证不重复
        file.transferTo(new File("E:\\IDEAWorkSpace\\GraduationProject\\gruaduation-project1\\web\\src\\main\\resources\\static\\images\\" + fileName));

        //设置要保存到数据库里面的照片名称
        commodity.setPhoto(fileName);
        productService.doCreate(commodity);

        return "/shopping/sell";
    }

}
