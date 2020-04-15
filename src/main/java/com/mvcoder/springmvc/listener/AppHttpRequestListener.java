package com.mvcoder.springmvc.listener;

import cn.hutool.core.date.DateUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by mvcoder on 2020/4/15.
 */
public class AppHttpRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        //System.out.println("a request finish..." + DateUtil.now());
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        String urlStr = "";
        if(servletRequestEvent.getServletRequest() instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
            urlStr = "url : " + request.getRequestURI() + " , queryPara : " + request.getQueryString();
        }
       // System.out.println("new request create..." + DateUtil.now() + " , " + urlStr);
    }
}
