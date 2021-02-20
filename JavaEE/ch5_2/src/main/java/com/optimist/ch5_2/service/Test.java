package com.optimist.ch5_2.service;

import com.optimist.ch5_2.conditional.MessagePrint;
import com.optimist.ch5_2.config.ConditionConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {


    private static AnnotationConfigApplicationContext content;


    public static void main(String[] args) {
//    // TODO Auto-generated method stub
//    @SuppressWarnings("resource")
//    ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//    TestDao tt = (TestDao)appCon.getBean("test");
//    tt.sayHello();

        content = new AnnotationConfigApplicationContext(ConditionConfig.class);
        MessagePrint mp = content.getBean(MessagePrint.class);
        System.out.println(mp.showMessage());

    }
}
