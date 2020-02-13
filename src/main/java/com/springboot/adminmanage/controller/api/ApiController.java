package com.springboot.adminmanage.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * author: ouyang
 * Date:2020/2/2 13:28
 **/
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/getPage")
    @ResponseBody
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        return modelAndView;

    }
}
