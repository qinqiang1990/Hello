package com.example.qinq.hello.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.SwipeListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ui.btn.SpinnerAdapter;
import com.example.qinq.hello.ui.btn.SpinnerBtn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_preferential)
public class PreferentialFragment extends BaseFragment {

    Context context;
    ListView listView;
    SwipeListViewAdapter adapter;

    @Override
    protected void initialize(View v) {
        context = getActivity();
        listView = (ListView) v.findViewById(R.id.preferential_listView);
    }

    private List<String> getData(int count) {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < count; i++)
            data.add(String.valueOf(i));
        return data;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {

        adapter = new SwipeListViewAdapter(context, getData(8));
        listView.setAdapter(adapter);

    }
}
