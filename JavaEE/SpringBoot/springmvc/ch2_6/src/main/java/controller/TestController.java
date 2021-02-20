package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    /*
    接收页面请求的JSON数据，并返回JSON格式的结果
     */
    @RequestMapping("/testJson")
    @ResponseBody
    public List<Map<String, Object>> testJson(@RequestBody Person user) {
        //打印接收的JSON格式的数据
        System.out.println(user.toString());

        /**
         * ArrayList<Person> allp = new ArrayList<Person>();
         *         Person p1 = new Person();
         *         p1.setName("秦");
         *         p1.setPassword("4561");
         *         p1.setAge(80);
         *
         *         Person p2 = new Person();
         *         p2.setName("加");
         *         p2.setPassword("4551");
         *         p2.setAge(100);
         *
         *         return allp;
         */

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "许志茹");
        map.put("password", "45612");
        map.put("age", "80");
//        return map;

        List<Map<String, Object>> allp = new ArrayList<Map<String, Object>>();
        allp.add(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "optimist");
        map1.put("password", "789155");
        map1.put("age", "100");
        allp.add(map1);
        return allp;

    }


}
