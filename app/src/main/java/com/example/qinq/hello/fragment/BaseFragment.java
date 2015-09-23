package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinq.hello.ioc.view.ContentView;

/**
 * Created by qinqiang on 2015/8/27.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        Class<? extends Fragment> clazz = this.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentViewLayoutId = contentView.value();
            rootView = inflater.inflate(contentViewLayoutId, container, false);
        }
        this.rootView = rootView;
        initialize();
        addListener();
        return rootView;
    }

    protected abstract void initialize();

    protected abstract void refresh();

    protected abstract void addListener();

    public void initNavBar(NavigateBar navBar) {
    }

    public void setTitle(String title) {
    }

}
