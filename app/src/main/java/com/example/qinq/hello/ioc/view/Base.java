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
     * ��ʼ��
     */
    protected abstract void initialize();

    /**
     * ����
     */
    protected abstract void addListener();

}
