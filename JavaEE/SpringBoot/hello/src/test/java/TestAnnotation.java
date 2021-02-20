import com.optimist.ConfigAnnotation;
import com.optimist.controller.TestController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnnotation {

    public static void main(String[] args) {

        //初始化Spring容器ApplicaionContext
        AnnotationConfigApplicationContext appCon =
                new AnnotationConfigApplicationContext(ConfigAnnotation.class);

        TestController tc = appCon.getBean(TestController.class);
        tc.save();
        appCon.close();

    }

}
