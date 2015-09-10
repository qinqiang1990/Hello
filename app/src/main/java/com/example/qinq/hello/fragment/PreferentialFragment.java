package com.example.qinq.hello.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.ListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_preferential)
public class PreferentialFragment extends BaseFragment {

    Context context;
    ListView listView;
    ListViewAdapter adapter;

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

        adapter = new ListViewAdapter(context, getData(8));
        listView.setAdapter(adapter);

    }
}
