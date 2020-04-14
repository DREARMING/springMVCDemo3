package com.mvcoder.springmvc.controller;

import cn.hutool.json.JSONUtil;
import com.mvcoder.springmvc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by mvcoder on 2020/4/10.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private User user;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @RequestMapping(value = "/test")
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                      javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("access TestController method!!");
        ModelAndView mav = new ModelAndView();
        mav.addObject("text","spring test 5");
        mav.addObject("text2","spring test 3");
        mav.setViewName("test");
        return mav;
    }

    //ResponseBody 表示返回结果用json格式化，返回的内容样式为 application-json
    //限制请求方法为 GET
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public User returnJsonTest(Integer id, String name, HttpServletRequest request){
        System.out.println(id + ", " + name + " , " + request.getParameter("name"));
        return user;
    }

    @RequestMapping(value = "/path/{p1}/{p2}")
    @ResponseBody
    public String testPath(@PathVariable int p1, @PathVariable String p2){
        return "hello test path : " + p1 + " , " + p2;
    }


    //用 @RequestPara 注解设置默认值和参数的必须性
    @RequestMapping(value = "/rpa")
    @ResponseBody
    public String testRequestParaAnnotation(@RequestParam(required = false, defaultValue = "1") int age){
        return "age : " + age;
    }

    //可以自动将参数转成对象中的属性
    @RequestMapping(value = "/entityParaConvert", method = RequestMethod.POST)
    @ResponseBody
    public String testEntityParaConvert(User user){
        return JSONUtil.toJsonStr(user);
    }


}
