package com.example.qinq.hello.fragment;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.baoyz.swipemenulistview.demo.MainActivity;
import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.view.ContentView;

import de.greenrobot.event.EventBus;
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
    Context context;

    @Override
    public void initNavBar(NavigateBar navBar) {
        navBar.setTitle("订单");
        navBar.addBackBtn(v -> {
            // TODO Auto-generated method stub
            EventBus.getDefault().post("HOME");
        });
        navBar.addSwitchIndicatorView("未消费", "已消费");

        super.initNavBar(navBar);
    }

    @Override
    protected void initialize() {
        context = this.getActivity();
        algorithm = new Algorithm();
        sum = (Button) rootView.findViewById(R.id.order_sum);
        tv = (TextView) rootView.findViewById(R.id.order_desc);
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {

        sum.setOnClickListener(v -> {

            tv.setText(String.valueOf(algorithm.plus(15f, 20f)));

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        });

    }
}
