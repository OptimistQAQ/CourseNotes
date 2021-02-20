package com.optimist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration  //通过注解来表明该类是一个Spring的配置类，相当于一个xml文件
@ComponentScan(basePackages = {"com.optimist.dao", "com.optimist.service"})
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
@EnableTransactionManagement    //开启声明式事务的支持
public class SpringJDBCConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    /*
    ***配置数据源
     */
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource myDataSource = new DriverManagerDataSource();
        //数据库驱动
        myDataSource.setDriverClassName(jdbcDriverClassName);
        //相应驱动的 jdbcUrl
        myDataSource.setUrl(jdbcUrl);
        //数据库的用户名
        myDataSource.setUsername(jdbcUsername);
        //数据库的密码
        myDataSource.setPassword(jdbcPassword);
        return myDataSource;
    }

    /*
    ***配置JdbcTemplate
     */
    @Bean(value = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /*
    * 为数据源添加事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dt = new DataSourceTransactionManager();
        dt.setDataSource(dataSource());
        return dt;
    }

}
