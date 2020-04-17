package com.mvcoder.springmvc;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

/**
 * 用来注入dao类的，因为dao接口设置了自动扫描生成的代理类没有办法通过注解的方式注入，所以借用 ApplicationContext 手动进行注入
 *
 * Created by mvcoder on 2020/4/17.
 */
public class DaoInjectUtil {

    private static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        DaoInjectUtil.context = context;
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

    public static <T> T getBean(String name){
        return (T) context.getBean(name);
    }

}
