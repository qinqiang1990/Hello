package com.example.qinq.hello;

import android.util.Log;

import com.example.qinq.hello.ioc.BaseActivity;
import com.example.qinq.hello.ioc.view.ContentView;

@ContentView(R.layout.test)
public class MainActivity extends BaseActivity {

    @Override
    protected void initialize() {
    Log.i("qinq","initialize");
    }

    @Override
    protected void addListener() {

    }
}
