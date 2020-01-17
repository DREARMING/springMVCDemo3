package com.mvcoder.springmvc.bean;

import org.springframework.beans.factory.annotation.Required;

/**
 * Created by mvcoder on 2020/1/17.
 */
public class Address {

    private int addressId;
    private String addressName;

    public Address(int addressId, String addressName) {
        this.addressId = addressId;
        this.addressName = addressName;
    }

    public Address(){}

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
