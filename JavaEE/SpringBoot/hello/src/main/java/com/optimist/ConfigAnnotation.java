package com.optimist;

/*
 ***Author: Optimist
 ***Time: 2020/9/22
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//声明当前类是一个配置类
@ComponentScan("com.optimist")
//自动扫描optimist包下使用的注解，并注册为Bean
/*
相当于在Spring的XML配置文件中使用<context:component - scan base - package = "Bean所在的包路径">
 */
public class ConfigAnnotation {
}
