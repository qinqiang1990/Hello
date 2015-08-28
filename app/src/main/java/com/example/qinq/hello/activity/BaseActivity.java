package com.example.qinq.hello.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.qinq.hello.ioc.ViewInjectUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initialize();
        addListener();
    }

    /**
     */
    protected abstract void initialize();

    /**
     * ¼àÌý
     */
    protected abstract void addListener();

}
