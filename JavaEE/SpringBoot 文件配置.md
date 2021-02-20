# SpringBoot 文件配置

## pom.xml文件配置

```java
// 必须要添加的依赖
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
	<dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.8.RELEASE</version>
    </dependency>
</dependencies>
```

## application.xml文件配置

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

	
    <!--    使用spring来创建我们的对象，在spring这些都成为bean-->
    <bean id="hello" class="com.optimist.dao.Hello">
        <property name="hello" value="Spring"/>
    </bean>

    <!--    id: 当前bean的唯一标识符
            class：定位到具体的Impl.java文件 -->
    <bean id="test" class="com.optimist.dao.TestDaoImp"/>

    <bean id="mysqlImpl" class="com.optimist.dao.UserDaoMysqlImpl"/>

</beans>
```

## Mytest测试文件

```java
public class Mytest {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//        Hello hello = (Hello)applicationContext.getBean("hello");
//        System.out.println(hello.toString());

        TestDaoImp tt = (TestDaoImp)applicationContext.getBean("test");
        tt.sayHello();

        UserDaoMysqlImpl userDaoMysql = (UserDaoMysqlImpl) applicationContext.getBean("mysqlImpl");
        userDaoMysql.sayHello();

        TestService testService = new TestServiceImpl();
        ((TestServiceImpl) testService).setTestDao(new UserDaoMysqlImpl());
        testService.sayHello();
    }
}
```



