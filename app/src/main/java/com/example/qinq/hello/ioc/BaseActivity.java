package com.example.qinq.hello.ioc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
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
