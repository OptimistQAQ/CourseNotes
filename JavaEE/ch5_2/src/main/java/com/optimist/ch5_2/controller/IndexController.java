package com.optimist.ch5_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//需要模板引擎的支持
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        /*
        * login代表逻辑视图名称，需要根据Spring MVC配置文件中
        * internalResourceViewResolver的前缀和后缀找到对应的物理视图
        */
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "Register";
    }

}
