package com.wp.demo.controller;

import com.wp.demo.bean.CommodityType;
import com.wp.demo.service.AnasislyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/2/14.
 */
@Controller
public class AnalysisController {

    @Autowired
    AnasislyService anasislyService;

    @RequestMapping(value = "/usr/analysis",method = RequestMethod.GET)
    public String analysisResult(HttpSession session,Model model){

        List<CommodityType> commodityType = anasislyService.getCommodityType();
        if(session.getAttribute("adminLoginUser") != null){

            model.addAttribute("commodityType",commodityType);
            System.out.println("commodityType:  " + commodityType);
            return "/adminPage/analysisResult";
        }
        model.addAttribute("commodityType",commodityType);
        return "/analysis/analysisResult";
    }
}
