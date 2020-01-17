package com.mvcoder.springmvc.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * Created by mvcoder on 2020/1/17.
 */
public class User {

    private int id;
    private String username;

    private Address address;

    public void init(){
        System.out.println("User class init method call");
    }

    public void destroy(){
        System.out.println("User class destroy method call");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void printInfo(){
        System.out.println("id : " + id + ", username:" + username + ", 居住于：" + address.getAddressName());
    }

}
