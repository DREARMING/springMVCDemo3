package com.mvcoder.springmvc.listener;

import cn.hutool.core.date.DateUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by mvcoder on 2020/4/15.
 */
public class AppHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
       // System.out.println("session create..." + DateUtil.now());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
       // System.out.println("session destroy..." + DateUtil.now());
    }
}
