import com.optimist.config.JavaConfig;
import com.optimist.instance.BeanClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        //初始化Spring 容器 ApplicationContext
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        BeanClass b1 = (BeanClass) appCon.getBean("beanClass");
        System.out.println(b1 + b1.message);
        BeanClass b2 = (BeanClass) appCon.getBean("beanStaticClass");
        System.out.println(b2 + b2.message);
        BeanClass b3 = (BeanClass) appCon.getBean("beanInstanceFactory");
        System.out.println(b3 + b3.message);
        appCon.close();
    }
}
