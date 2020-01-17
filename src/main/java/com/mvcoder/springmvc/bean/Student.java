package com.mvcoder.springmvc.bean;

import com.mvcoder.springmvc.aop.FunctionWasteTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 通过注解的方式 声明一个 bean， 并且配置其作用域为 singleton
 * 可以通过value 设置 属性默认值
 * 通过 @Autowired 根据类型注入，并且可以通过@Qualifier指定具体类型的bean
 *
 * Created by mvcoder on 2020/1/17.
 */
@Component("student")
@Scope("singleton")
public class Student {

    @Value("1258235316")
    private int id;

    @Value("mvcoder")
    private String name;

    @Autowired
    @Qualifier("address2")
    private Address address;

    //用于指定初始化方法（用在方法上）
    @PostConstruct
    public void init(){
        System.out.println("student class call init method");
    }

    //用于指定销毁方法
    @PreDestroy
    public void destroy(){
        System.out.println("student class call destroy method");
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //用注解来查看方法执行的耗时
    @FunctionWasteTime
    public void printInfo() {
        System.out.println("id : " + id + ", username:" + name + ", 学校：" + address.getAddressName());
    }


    public void method2(){
        System.out.println("call methoid 2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void method3(){
        System.out.println("call method 3");
        //测试异常捕捉
        //throw new IllegalArgumentException("throw a error");
    }
}
