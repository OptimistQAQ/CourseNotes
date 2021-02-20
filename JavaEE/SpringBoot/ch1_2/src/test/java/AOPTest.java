import com.optimist.AspectjAOPConfig;
import com.optimist.dao.TestDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        //初始化Spring容器中的ApplicationContext
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(AspectjAOPConfig.class);

        //从容器中获取增强后的目标对象
        TestDao testDao = appCon.getBean(TestDao.class);

        //执行方法
        testDao.save();
        System.out.println("==============\n");
        testDao.modify();
        System.out.println("==============\n");
        testDao.delete();
        appCon.close();
    }
}
