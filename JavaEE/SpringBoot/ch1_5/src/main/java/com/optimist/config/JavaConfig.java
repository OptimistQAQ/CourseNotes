package com.optimist.config;

import com.optimist.instance.BeanClass;
import com.optimist.server.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    /*
    **构造方法实例化
     */
    @Bean(value = "beanClass")
    public BeanClass getBeanClass() {
        return new BeanClass();
    }

    /*
    ***静态工厂实例化
     */
    @Bean(value = "beanStaticClass")
    public BeanClass getBeanStaticFactory() {
        return new BeanClass();
    }

    /*
    ****实例工厂化
     */
    @Bean(value = "beanInstanceFactory")
    public BeanClass getBeanInstanceFactory() {
        return new BeanClass();
    }


    @Bean(initMethod = "initService", destroyMethod = "destroyService")
    public MyService getMyService() {
        return new MyService();
    }

}
