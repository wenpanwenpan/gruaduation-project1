package com.wp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/2/14.
 */
@Controller
public class AnalysisController {


    @RequestMapping(value = "/usr/analysis",method = RequestMethod.GET)
    public String analysisResult(HttpSession session){

        if(session.getAttribute("adminLoginUser") != null){
            return "/adminPage/analysisResult";
        }

        return "/analysis/analysisResult";
    }
}
