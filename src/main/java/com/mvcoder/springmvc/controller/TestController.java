package com.mvcoder.springmvc.controller;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Created by mvcoder on 2020/4/10.
 */
public class TestController implements Controller {

    @Nullable
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                      javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("access TestController method!!");
        ModelAndView mav = new ModelAndView();
        mav.addObject("text","spring test 5");
        mav.addObject("text2","spring test 3");
        mav.setViewName("test");
        return mav;
    }
}
