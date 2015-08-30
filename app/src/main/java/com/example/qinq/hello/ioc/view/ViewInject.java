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


    int DEFAULT_ID = -1;


    int value() default DEFAULT_ID;

}