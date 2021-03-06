package com.example.qinq.hello.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.ListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ui.btn.ExpandListBtn;
import com.example.qinq.hello.ui.btn.SpinnerBtn;

import java.util.ArrayList;
import java.util.List;

import qinq.library.QinqSwipeListView;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_shop)
public class ShopFragment extends BaseFragment {

    Context context;
    SpinnerBtn spinnerBtn;
    ExpandListBtn expandListBtn;

    QinqSwipeListView qinqSwipeListView;
    ListViewAdapter adapter;

    @Override
    public void initNavBar(NavigateBar navBar) {
        navBar.setTitle("餐店");
        super.initNavBar(navBar);
    }

    @Override
    protected void initialize() {

        context = getActivity();
        spinnerBtn = (SpinnerBtn) rootView.findViewById(R.id.shop_spinner_order);
        spinnerBtn.setDataSource(getData(14));

        expandListBtn = (ExpandListBtn) rootView.findViewById(R.id.shop_expandListBtn_area);
        expandListBtn.setDataSource(getData(15));

        qinqSwipeListView = (QinqSwipeListView) rootView.findViewById(R.id.shop_listView);
        adapter = new ListViewAdapter(context, getData(10));
        qinqSwipeListView.setAdapter(adapter);
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

        spinnerBtn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }

        });

        expandListBtn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }

        });


        qinqSwipeListView.setOnDismissCallback(new QinqSwipeListView.OnDismissCallback() {

            @Override
            public void onDismiss(int dismissPosition) {
                adapter.getData().remove(dismissPosition);
            }

        });


        qinqSwipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(context, String.valueOf(adapter.getItem(position)), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
