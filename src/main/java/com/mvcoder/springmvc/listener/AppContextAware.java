package com.mvcoder.springmvc.listener;

import com.mvcoder.springmvc.DaoInjectUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by mvcoder on 2020/4/17.
 */
@Component
public class AppContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SetAppContext");
        DaoInjectUtil.setContext(applicationContext);
    }
}
