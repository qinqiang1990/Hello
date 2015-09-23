package com.example.qinq.hello.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.SwipeListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.fortysevendeg.swipelistview.SwipeListView;


import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_personal)
public class PersonalFragment extends BaseFragment {

    SwipeListView swipeListView;
    Context context;
    SwipeListViewAdapter swipeListViewAdapter;

    @Override
    public void initNavBar(NavigateBar navBar) {
        navBar.setTitle("个人");
        super.initNavBar(navBar);
    }


    @Override
    protected void initialize() {
        context = this.getActivity();
        swipeListView = (SwipeListView) rootView.findViewById(R.id.personal_listView);
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
