package com.example.qinq.hello.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.qinq.hello.ioc.ViewInjectUtils;

public abstract class BaseFragmentActivity extends FragmentActivity {

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

    /**
     * 切换Fragment
     */

    protected void switchFragment(Fragment from, Fragment to) {

        if (from == to) {
            return;
        } else {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            transaction.hide(from).show(to).commit();
        }

    }
}
