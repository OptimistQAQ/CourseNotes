package com.optimist.ch5_2.conditional;

public class MyMessagePrint implements MessagePrint {
    @Override
    public String showMessage() {
        return "test.properties文件存在。";
    }
}
