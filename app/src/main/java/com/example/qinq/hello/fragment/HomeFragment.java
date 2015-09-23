package com.example.qinq.hello.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.data.GridViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    Context context;
    LinearLayout recommendLayout;
    GridView brandGridView;
    LinearLayout newFoodLayout;
    GridView shopGridView;

    @Override
    public void initNavBar(NavigateBar navBar) {
        navBar.setTitle("首页");
        super.initNavBar(navBar);
    }

    @Override
    protected void initialize() {
        context = getActivity();
        recommendLayout = (LinearLayout) rootView.findViewById(R.id.recommendLayout);
        newFoodLayout = (LinearLayout) rootView.findViewById(R.id.newFoodLayout);
        brandGridView = (GridView) rootView.findViewById(R.id.brandGridView);
        shopGridView = (GridView) rootView.findViewById(R.id.shopGridView);
        loadRecommand(3);

        loadNewfood(8);
        shopGridView.setAdapter(getData(9));

        brandGridView.setAdapter(getData(9));

        brandGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });


    }

    private GridViewAdapter getData(int count) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", String.valueOf(i));
            list.add(map);
        }
        GridViewAdapter adapter = new GridViewAdapter(context, list);
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


    private void loadNewfood(int count) {
        for (int i = 0; i < count; i++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.index_newfood_item, null);
            ((TextView) item.findViewById(R.id.ini_foodNameView)).setText(String.valueOf(i));
            newFoodLayout.addView(item);
        }

    }
}
