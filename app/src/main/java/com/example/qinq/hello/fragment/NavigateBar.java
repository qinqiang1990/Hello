package com.example.qinq.hello.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinq.hello.R;

/**
 * Created by qinqiang on 2015/8/27.
 */
public class NavigateBar extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("qinq", "NavigateBar_onCreateView");
        return inflater.inflate(R.layout.navigate, container, false);
    }

}
