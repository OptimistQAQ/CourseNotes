package com.optimist.service;

import com.optimist.dao.TestDao;
import com.optimist.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    public TestDao testDao;

    @Override
    public void testJDBC() {

        String insertSql = "insert into user values(null, ?, ?)";

        //数组parm的值与insertSql语句中的一一对应
        Object param1[] = {"chenheng1", "男"};
        Object param2[] = {"chenheng2", "女"};
        Object param3[] = {"chenheng3", "男"};
        Object param4[] = {"chenheng4", "男"};

        String insertSql1 = "insert into user values(null, ?, ?)";

        Object param5[] = {"1", "chenheng5", "女"};
        Object param6[] = {"2", "chenheng6", "女"};

        try {

            //添加用户
            testDao.update(insertSql, param1);
            testDao.update(insertSql, param2);
            testDao.update(insertSql, param3);
            testDao.update(insertSql, param4);

//            //添加两个ID相同的用户，出现唯一性约束异常
//            testDao.update(insertSql1, param5);
//            testDao.update(insertSql1, param6);

            //查询用户
            String selectSql = "select * from user";
            List<MyUser> list = testDao.query(selectSql, null);
            for (MyUser mu : list) {
                System.out.println(mu);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("主键重复，事务回滚");
        }
    }
}
