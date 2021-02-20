package com.optimist.instance;

public class BeanClass {
    public String message;

    public BeanClass() {
        message = "构造方法实例化Bean";
    }

    public BeanClass(String message) {
        this.message = message;
    }
}
