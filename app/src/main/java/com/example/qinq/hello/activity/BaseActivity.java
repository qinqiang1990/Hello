package com.example.qinq.hello.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.qinq.hello.ioc.ViewInjectUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.instance().inject(this);
        initialize();
        addListener();
    }

    /**
     * 初始化
     */
    protected abstract void initialize();

    /**
     * 刷新
     */
    protected abstract void refresh();

    /**
     * 监听
     */
    protected abstract void addListener();

}
