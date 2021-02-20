package com.optimist.ch5_2.config;


import com.optimist.ch5_2.conditional.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(MyCondition.class)
    public MessagePrint myMessagePrint() {
        return new MyMessagePrint();
    }

    @Bean
    @Conditional(YourCondition.class)
    public MessagePrint yourMessagePrint() {
        return new YourMessagePrint();
    }

}
