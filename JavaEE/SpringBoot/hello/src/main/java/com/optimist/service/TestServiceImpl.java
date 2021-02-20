package com.optimist.service;

/*
 ***Author: Optimist
 ***Time: 2020/9/22
 */
import com.optimist.dao.TestDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource(name="testDao")
    /*
    ****相当于@Autowired, @Autowired默认按照Bean类型进行注入
     */
    private TestDao testDao;

    //利用set进行动态值注入
    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public void sayHello() {
        testDao.sayHello();
        System.out.println("testService save");
    }
}
