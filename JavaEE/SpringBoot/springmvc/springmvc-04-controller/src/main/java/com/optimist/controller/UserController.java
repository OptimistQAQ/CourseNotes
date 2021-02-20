package com.optimist.controller;

import com.optimist.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {


    //localhost:8080/user/t1?name=xxx
    @RequestMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model) {
        //1.接收前端参数
        System.out.println("接收到的前端参数为：" + name);
        //将返回的结果传递给前端
        model.addAttribute("msg", name);

        //3. 试图跳转
        return "test";
    }

    //前端接受的是一个对象


    /*
    * 1. 接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
    * 1. 假设传递的是一个对象User， 匹配User对象中的字段名， 如果名字一致则ok，否则匹配不到
     */
    @RequestMapping("/t2")
    public String test2(User user, Model model) {
        System.out.println(user);
        model.addAttribute("msg", user);
        return "test";
    }
}
