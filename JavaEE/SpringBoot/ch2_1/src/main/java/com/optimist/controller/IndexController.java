package com.optimist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        /*
         * login代表逻辑视图名称，需要根据Spring MVC配置文件中
         * internalResourceViewResolver的前缀和后缀找到对应的物理视图
         */
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "Register";
    }
}
