package com.optimist.ch5_2.controller;


import com.optimist.ch5_2.pojo.Dog;
import com.optimist.ch5_2.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* @Controller 表示IndexController的实例是一个控制器
* @Controller 相当于@Controller("indexController")
 */
@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @Autowired
    Dog dog;

    @Autowired
    Person person;

    @RequestMapping("/testEnv")
    public String testEnv() {
        return "方法一：" + env.getProperty("test.msg");
    }

    @RequestMapping("/hello")
    public String hello() {
        //调用业务
        return "hello, World";
    }

    @RequestMapping("/qin")
    public String say() {
        return "Mercy Qin!";
    }

    @RequestMapping("/t1")
    public String test() {
        String test = "{\n"
                        + "   {"+ "秦嘉豪" + "},\n"
                        + "   {" + "男" + "},\n}";
        return test;
    }

    @RequestMapping("/dogTest")
    public String dogTest() {
        return "主人: " + person.getName() + "       " + dog.toString();
    }

}
