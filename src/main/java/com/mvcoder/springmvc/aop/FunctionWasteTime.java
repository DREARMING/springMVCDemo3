package com.mvcoder.springmvc.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by mvcoder on 2020/1/17.
 */
@Target({ElementType.METHOD})
public @interface FunctionWasteTime {
}
