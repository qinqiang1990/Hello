package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.view.ContentView;

import qinq.IAlgorithm;
import qinq.impl.Algorithm;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_order)
public class OrderFragment extends BaseFragment {

    IAlgorithm algorithm;
    Button sum;
    TextView tv;

    @Override
    protected void initialize(View v) {
        algorithm = new Algorithm();
        sum = (Button) v.findViewById(R.id.order_sum);
        tv = (TextView) v.findViewById(R.id.order_desc);
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv.setText(String.valueOf(algorithm.plus(15f, 20f)));

            }
        });
    }
}
