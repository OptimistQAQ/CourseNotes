package com.optimist.server;

public class MyService {

    public void initService() {
        System.out.println("initServer");
    }

    public MyService() {
        System.out.println("构造方法");
    }

    public void destroyService() {
        System.out.println("destroyService");
    }
}
