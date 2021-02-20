import com.optimist.config.JavaConfig;
import com.optimist.server.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestInitAndDestroy {
    public static void main(String[] args) {
        //初始化Spring 容器 ApplicationContext
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        MyService ms = appCon.getBean(MyService.class);
        System.out.println(ms);
        appCon.close();
    }
}
