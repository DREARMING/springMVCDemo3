package com.mvcoder.springmvc.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Aspect 注解声明该类是一个 切面类，类中声明一切 PointCut 规则，用于切入方法的执行
 *
 * 需要在xml或者config类中 开启 AutoProxy
 *
 * Created by mvcoder on 2020/1/17.
 */
@Component
@Aspect
public class AOPTest {


    //Around 注解是切入 PointCut 的前后
    @Around("@annotation(com.mvcoder.springmvc.aop.FunctionWasteTime)")
    public Object countFunctionWasteTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("start call method : "  + joinPoint.toShortString());
        long startTime = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        System.out.println("after call method : " + joinPoint.toShortString() + " , waste time : " + (System.currentTimeMillis() - startTime));
        return obj;
    }


    /**
     * 切入Student的所有方法
     * 第一个 * 代表包含所有访问权限，任意返回类型的方法
     * 第二个 * 代表是 Student 类的所有方法
     * .. 代表任意多个参数
     */
    @Pointcut("execution(* com.mvcoder.springmvc.bean.Student.*(..))")
    public void pointCut(){

    }

    @Before(value = "com.mvcoder.springmvc.aop.AOPTest.pointCut()")
    public void before(JoinPoint joinpoint){
        System.out.println("before call : " + joinpoint.toShortString());
    }


    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after call : " + joinPoint.toShortString());
    }

    @AfterThrowing(value = "pointCut()")
    public void afterThrowing(JoinPoint joinpoint){
        System.out.println("after throw : " + joinpoint.toShortString());
    }



}
