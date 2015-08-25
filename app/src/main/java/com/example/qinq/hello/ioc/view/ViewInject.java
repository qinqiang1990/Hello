package com.example.qinq.hello.ioc.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by qinqiang on 2015/8/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {


    /**
     * 默认控件ID
     */
    int DEFAULT_ID = -1;

    /**
     * 默认方法
     */
    String DEFAULT_METHOD = "";


    int value() default DEFAULT_ID;


    String click() default DEFAULT_METHOD;
}