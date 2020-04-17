package com.mvcoder.springmvc.dao;

import com.beust.jcommander.Parameter;
import com.mvcoder.springmvc.bean.User;

/**
 * Created by mvcoder on 2020/4/17.
 */
public interface UserDao {


    public User selectUser(Integer id);

}
