import com.optimist.config.ScopeConfig;
import com.optimist.server.PrototypeService;
import com.optimist.server.SingletonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestScope {
    public static void main(String[] args) {
        //初始化Spring 容器 ApplicationContext
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(ScopeConfig.class);

        SingletonService ss1 = appCon.getBean(SingletonService.class);
        SingletonService ss2 = appCon.getBean(SingletonService.class);
        System.out.println(ss1);
        System.out.println(ss2);

        PrototypeService ps1 = appCon.getBean(PrototypeService.class);
        PrototypeService ps2 = appCon.getBean(PrototypeService.class);
        System.out.println(ps1);
        System.out.println(ps2);
        appCon.close();
    }
}
