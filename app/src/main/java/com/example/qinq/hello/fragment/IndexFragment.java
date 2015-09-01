package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.GridViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_index)
public class IndexFragment extends BaseFragment {

    LinearLayout recommendLayout;
    GridView brandGridView;

    @Override
    protected void initialize(View v) {
        recommendLayout = (LinearLayout) v.findViewById(R.id.recommendLayout);
        brandGridView = (GridView) v.findViewById(R.id.brandGridView);
        loadRecommand(3);

        brandGridView.setAdapter(getData(10));

    }

    private GridViewAdapter getData(int count) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", String.valueOf(i));
            list.add(map);
        }
        GridViewAdapter adapter = new GridViewAdapter(this.getActivity(), list);
        return adapter;
    }

    @Override
    protected void refresh() {
        loadRecommand(3);
    }

    @Override
    protected void addListener() {

    }

    private void loadRecommand(int count) {
        for (int i = 0; i < count; i++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.index_recommend_item, null);
            ((TextView) item.findViewById(R.id.recommend_1)).setText("One");
            ((TextView) item.findViewById(R.id.recommend_2)).setText("Two");
            ((TextView) item.findViewById(R.id.recommend_3)).setText("Three");
            recommendLayout.addView(item);
        }

    }

}
