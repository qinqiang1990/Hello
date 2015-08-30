package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_index)
public class IndexFragment extends BaseFragment {

    LinearLayout recommendLayout;

    @Override
    protected void initialize(View v) {
        recommendLayout = (LinearLayout) v.findViewById(R.id.recommendLayout);
        loadRecommand();

    }

    @Override
    protected void refresh() {
        loadRecommand();
    }

    @Override
    protected void addListener() {

    }

    private void loadRecommand() {
        View item = LayoutInflater.from(getActivity()).inflate(R.layout.index_recommend_item, null);
        recommendLayout.addView(item);
    }

}
