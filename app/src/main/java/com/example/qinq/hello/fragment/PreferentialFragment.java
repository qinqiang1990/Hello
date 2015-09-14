package com.example.qinq.hello.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qinq.hello.R;
import com.example.qinq.hello.common.DensityUtil;
import com.example.qinq.hello.ioc.data.ListViewAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import org.json.JSONObject;

import java.util.List;

import qinq.library.SwipeListViewAdapter;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_preferential)
public class PreferentialFragment extends BaseFragment {

    Context context;
    ListView listView;
    ListViewAdapter adapter;
    SwipeListViewAdapter swipeListViewAdapter;
    Dialog custom_dialog;
    private RequestQueue mRequestQueue;
    private Button refresh, white, black, green, blue, red;
    private boolean isShow = false;

    @Override

    protected void initialize(View v) {
        context = getActivity();

        mRequestQueue = Volley.newRequestQueue(context);

        listView = (ListView) v.findViewById(R.id.preferential_listView);

        refresh = (Button) v.findViewById(R.id.preferential_refresh);


        white = (Button) v.findViewById(R.id.nine_white);
        red = (Button) v.findViewById(R.id.nine_red);
        blue = (Button) v.findViewById(R.id.nine_blue);
        black = (Button) v.findViewById(R.id.nine_black);
        green = (Button) v.findViewById(R.id.nine_green);


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
                    animationShow(black, 1, 5, 300);
                    animationShow(blue, 2, 5, 300);
                    animationShow(red, 3, 5, 300);
                    animationShow(green, 4, 5, 300);
                } else {
                    isShow = false;
                    animationHint(black, 1, 5, 300);
                    animationHint(blue, 2, 5, 300);
                    animationHint(red, 3, 5, 300);
                    animationHint(green, 4, 5, 300);
                }
            }
        });

    }
}
