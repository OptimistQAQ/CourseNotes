import com.optimist.dao.Hello;
import com.optimist.dao.TestDaoImp;
import com.optimist.dao.UserDaoMysqlImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.optimist.service.TestService;
import com.optimist.service.TestServiceImpl;

public class MyTest {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Hello hello = (Hello)applicationContext.getBean("hello");
        System.out.println(hello.toString());

        TestDaoImp tt = (TestDaoImp)applicationContext.getBean("test");
        tt.sayHello();

        UserDaoMysqlImpl userDaoMysql = (UserDaoMysqlImpl) applicationContext.getBean("mysqlImpl");
        userDaoMysql.sayHello();

        TestService testService = new TestServiceImpl();
        ((TestServiceImpl) testService).setTestDao(new UserDaoMysqlImpl());
        testService.sayHello();
    }
}
