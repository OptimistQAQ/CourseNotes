package com.optimist.dao;

/*
 ***Author: Optimist
 ***Time: 2020/9/22
 */
import org.springframework.stereotype.Repository;

/*
***相当于@Repository，但是如果在service层使用@Resource(name="testDao")注入Bean,testDao不能省略
 */
@Repository("testDao")
public class TestDaoImp implements TestDao {

    public void sayHello() {
        System.out.println("Hello, Study hard!");
    }

}
