package com.optimist.annotation;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
***切面类，在此类中编写各种类型的通知
 */
@Aspect       //声明一个切面
@Component    //让切面成为Spring容器管理的Bean
public class MyAspect {
    /*
    定义切入点，通知增强哪写方法。
    "execution(* com.optimist.dao.*.*(..))"是导入包中
    该切入表达式表示匹配aspectj.dao.中任意类的任意方法的执行
     */

    @Pointcut("execution(* com.optimist.dao.*.*(..))")
    private void myPointCut() {

    }

    /*
    *前置通知，使用Joinpoint接口作为参数获得目标的对象信息
     */
    @Before("myPointCut()")
    public void before(JoinPoint jp) {
        System.out.println("前置通知，模拟权限控制");
        System.out.println(", 目标类对象：" + jp.getTarget().toString()
        + "，\n被增强的方法：" + jp.getSignature().getName());
    }

    /*
     *前置通知，使用Joinpoint接口作为参数获得目标的对象信息
     */
    @AfterReturning("myPointCut()")
    public void afterReturning(JoinPoint jp) {
        System.out.println("后置返回通知：" + "模拟删除的临时文件");
        System.out.println("，\n被增强的方法：" + jp.getSignature().getName());
    }

    /*
    *环绕通知
    *ProceedingJoinPoint是JoinPoint子接口，代表可以执行的目标方法
     */
    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //开始
        System.out.println("环绕开始，执行目标方法前，模拟开启事务");
        //执行当前的目标方法
        Object obj = pjp.proceed();
        //结束
        System.out.println("环绕结束，执行目标方法后，模拟关闭事务");
        return obj;
    }

    /*
    *异常通知
     */
    @AfterThrowing(value = "myPointCut()", throwing = "e")
    public void except(Throwable e) {
        System.out.println("异常通知：" + "程序执行异常" + e.getMessage());
    }

    /*
    *后置通知
     */
    @After("myPointCut()")
    public void after() {
        System.out.println("最终通知：模拟释放资源");
    }

}
