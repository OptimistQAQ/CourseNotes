package com.optimist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test2Controller {

    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "HelloController");
        return "test";
    }

}
