package com.wp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2019/1/2.
 */
@Controller
public class ShoppingController {

    @GetMapping("/shopping/goshopping")
    public String shoppingFirstPage(){

        return "/shopping/myshopping";
    }
}
