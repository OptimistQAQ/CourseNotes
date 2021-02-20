package com.optimist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //声明一个配置类
@ComponentScan("com.optimist")  //自动扫描该包中的注解
@EnableAspectJAutoProxy  //开启Spring对AspectJ的支持
public class AspectjAOPConfig {
}
