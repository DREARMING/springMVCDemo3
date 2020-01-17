package com.mvcoder.springmvc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by mvcoder on 2020/1/17.
 */
@Component("app")
@Scope("singleton")
public class SpringMVCApp implements ApplicationListener<ContextStartedEvent> {


    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("application start, you can init some operation");
    }
}
