import com.optimist.config.SpringJDBCConfig;
import com.optimist.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJDBC {
    public static void main(String[] args) {
        //初始化Spring容器
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(SpringJDBCConfig.class);

        TestService ts = appCon.getBean(TestService.class);
        ts.testJDBC();
        appCon.close();
    }
}
