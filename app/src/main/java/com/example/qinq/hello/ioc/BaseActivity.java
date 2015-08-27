package com.example.qinq.hello.ioc;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
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
