package com.optimist.controller;

/*
***Author: Optimist
***Time: 2020/9/22
 */
import com.optimist.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    public TestService getTestService() {
        return testService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String toString() {
        return "TestController{" +
                "testService=" + testService +
                '}';
    }

    public void save() {
        System.out.println("testController");
        testService.sayHello();
    }

}
