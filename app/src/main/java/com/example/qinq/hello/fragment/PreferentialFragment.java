package com.example.qinq.hello.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.qinq.hello.R;
import com.example.qinq.hello.common.DensityUtil;
import com.example.qinq.hello.ioc.data.ListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import org.json.JSONObject;

import java.util.List;

import qinq.library.SwipeListViewAdapter;
import qinq.library.common.AppRun;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_preferential)
public class PreferentialFragment extends BaseFragment {

    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;
    private SwipeMenuListView swipeMenuListView;


    Context context;
    ListView listView;
    ListViewAdapter adapter;
    SwipeListViewAdapter swipeListViewAdapter;
    Dialog custom_dialog;
    private RequestQueue mRequestQueue;
    private Button refresh, white, black, blue, red;
    private boolean isShow = false;


    @Override
    public void initNavBar(NavigateBar navBar) {
        navBar.setTitle("优惠");
        super.initNavBar(navBar);
    }


    @Override
    protected void initialize() {
        context = getActivity();

        mRequestQueue = Volley.newRequestQueue(context);

        listView = (ListView) rootView.findViewById(R.id.preferential_listView);

        swipeMenuListView = (SwipeMenuListView) rootView.findViewById(R.id.preferential_swipe_listView);


        refresh = (Button) rootView.findViewById(R.id.preferential_refresh);


        white = (Button) rootView.findViewById(R.id.nine_white);
        red = (Button) rootView.findViewById(R.id.nine_red);
        blue = (Button) rootView.findViewById(R.id.nine_blue);
        black = (Button) rootView.findViewById(R.id.nine_black);

        mAppList = context.getPackageManager().getInstalledApplications(0);
        mAdapter = new AppAdapter();
        swipeMenuListView.setAdapter(mAdapter);


        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        context.getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(qinq.library.common.DensityUtils.dp2px(context, 90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        context.getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(qinq.library.common.DensityUtils.dp2px(context, 90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        swipeMenuListView.setMenuCreator(creator);

    }

    private void BindData(String url, final Boolean show) {


        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String text = "[\"中秋大优惠\",\"七夕优惠劵大赠送\",\"中秋优惠劵大赠送\",\"国庆优惠劵大赠送\"]";

                List<String> data = JSON.parseArray(text, String.class);


                swipeListViewAdapter = new SwipeListViewAdapter(context, data);

                //adapter = new ListViewAdapter(context, data);
                listView.setAdapter(swipeListViewAdapter);


                if (show && custom_dialog != null) {
                    custom_dialog.cancel();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String text = "[\"ONE\",\"TWO\",\"THREE\"]";
                List<String> data = JSON.parseArray(text, String.class);

                adapter = new ListViewAdapter(context, data);
                listView.setAdapter(adapter);

                if (show && custom_dialog != null) {
                    custom_dialog.cancel();
                }
            }
        });
        mRequestQueue.add(jr);

        if (show) {
            CustomDialog(jr);
        }
    }

    @Override
    protected void refresh() {

        String url = "http://www.dcai100.com/memberCoupon/list?pageAt=1&userId=1229b68da21c43dc91a72f8b95f551cd";
        BindData(url, true);


    }


    public void CustomDialog(final JsonObjectRequest jsonObjectRequest) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.customdialog, null);
        custom_dialog = new AlertDialog.Builder(context).create();
        custom_dialog.show();
        custom_dialog.getWindow().setContentView(layout);
        custom_dialog.getWindow().setLayout(DensityUtil.dip2px(context, 200), DensityUtil.dip2px(context, 35));
        custom_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                jsonObjectRequest.cancel();
            }
        });

    }

    private void animationShow(View view, int index, int total, int radius) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = index * Math.PI / (2 * total);
        int translateX = (int) ((int) radius * Math.sin(degree));
        int translateY = (int) ((int) radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translateX),
                ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() - translateY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f));
        set.start();

    }


    private void animationHint(View view, int index, int total, int radius) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);

        }

        double degree = index * Math.PI / (2 * total);
        int translateX = (int) ((int) radius * Math.sin(degree));
        int translateY = (int) ((int) radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translateX, 0),
                ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() + translateY),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        set.start();


    }

    @Override
    protected void addListener() {

        String url = "http://www.dcai100.com/memberCoupon/list?pageAt=1&userId=1229b68da21c43dc91a72f8b95f551cd";
        BindData(url, false);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    isShow = true;
                    animationShow(black, 1, 6, 300);
                    animationShow(blue, 3, 6, 300);
                    animationShow(red, 5, 6, 300);

                } else {
                    isShow = false;
                    animationHint(black, 1, 6, 300);
                    animationHint(blue, 3, 6, 300);
                    animationHint(red, 5, 6, 300);
                }
            }
        });


        // step 2. listener item click event
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                ApplicationInfo item = mAppList.get(position);
                switch (index) {
                    case 0:
                        // open
                        AppRun.open(context, item);
                        break;
                    case 1:
                        // delete
                        // AppRun.delete(context, item);
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }


    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context.getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(item.loadIcon(context.getPackageManager()));
            holder.tv_name.setText(item.loadLabel(context.getPackageManager()));
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }
}
