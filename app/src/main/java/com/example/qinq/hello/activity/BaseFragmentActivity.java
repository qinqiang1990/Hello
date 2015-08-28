package com.example.qinq.hello.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.qinq.hello.ioc.ViewInjectUtils;

public abstract class BaseFragmentActivity extends FragmentActivity {

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

    /**
     * ÇÐ»»Fragment
     */

    protected void switchFragment(Fragment from, Fragment to) {

        if (from == to) {
            return;
        } else {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            transaction.hide(from).show(to).commit();
        }
        from = to;


    }
}
