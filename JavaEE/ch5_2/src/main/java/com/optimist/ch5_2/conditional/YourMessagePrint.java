package com.optimist.ch5_2.conditional;

public class YourMessagePrint implements MessagePrint {
    @Override
    public String showMessage() {
        return "test.properties文件不存在";
    }
}
