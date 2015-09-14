package com.example.qinq.hello.fragment;

import android.content.Context;
import android.view.View;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.SwipeListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.fortysevendeg.swipelistview.SwipeListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_personal)
public class PersonalFragment extends BaseFragment {

    SwipeListView swipeListView;
    Context context;
    SwipeListViewAdapter swipeListViewAdapter;

    @Override
    protected void initialize(View v) {
        context = this.getActivity();
        swipeListView = (SwipeListView) v.findViewById(R.id.personal_listView);

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {

        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            data.add(String.valueOf(i));
        }
        swipeListViewAdapter = new SwipeListViewAdapter(context, data, swipeListView);
        swipeListView.setAdapter(swipeListViewAdapter);
    }
}
