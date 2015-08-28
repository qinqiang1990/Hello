package com.example.qinq.hello.ioc.view;

import android.app.Activity;

import com.example.qinq.hello.ioc.ViewInjectUtils;

public abstract class Base {

    protected void inject(Activity activity) {
        ViewInjectUtils.inject(activity);
        initialize();
        addListener();
    }

    /**
     * ³õÊ¼»¯
     */
    protected abstract void initialize();

    /**
     * ¼àÌý
     */
    protected abstract void addListener();

}
