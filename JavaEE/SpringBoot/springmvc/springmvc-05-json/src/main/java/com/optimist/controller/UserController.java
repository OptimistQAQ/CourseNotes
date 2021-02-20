package com.optimist.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimist.pojo.User;
import com.optimist.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    //, produces = "application/json;charset=utf-8"
    @RequestMapping(value = "/j1")
    @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {

        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user1 = new User("qin1", 3, "男");

        String  str = mapper.writeValueAsString(user1);

        return str;
    }

    @RequestMapping(value = "/j2")
    @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json2() throws JsonProcessingException {

        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<User>();

        //创建一个对象
        User user1 = new User("qin1", 3, "男");
        User user2 = new User("qin2", 3, "男");
        User user3 = new User("qin3", 3, "男");
        User user4 = new User("qin4", 3, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String  str = mapper.writeValueAsString(userList);

        return str;
    }

    @RequestMapping(value = "/j3")
    @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json3() throws JsonProcessingException {

        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        Date date = new Date();

        //自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  str = mapper.writeValueAsString(sdf.format(date));

        return str;
    }

    @RequestMapping(value = "/j4")
    @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json4() throws JsonProcessingException {

        Date date = new Date();
        return JsonUtils.getJson(date);
    }

    @RequestMapping(value = "/j5")
    @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json5() throws JsonProcessingException {

        List<User> userList = new ArrayList<User>();

        //创建一个对象
        User user1 = new User("秦嘉豪1", 3, "男");
        User user2 = new User("秦嘉豪2", 3, "男");
        User user3 = new User("秦嘉豪3", 3, "男");
        User user4 = new User("秦嘉豪4", 3, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String str = JSON.toJSONString(userList);
        return str;
    }

}
