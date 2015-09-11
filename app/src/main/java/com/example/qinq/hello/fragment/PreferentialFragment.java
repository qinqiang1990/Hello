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
import android.widget.Toast;

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

import org.json.JSONObject;

import java.util.List;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.fragment_preferential)
public class PreferentialFragment extends BaseFragment {

    Context context;
    ListView listView;
    ListViewAdapter adapter;
    Dialog custom_dialog;
    private RequestQueue mRequestQueue;
    private Button refresh;

    @Override

    protected void initialize(View v) {
        context = getActivity();

        mRequestQueue = Volley.newRequestQueue(context);

        listView = (ListView) v.findViewById(R.id.preferential_listView);

        refresh = (Button) v.findViewById(R.id.preferential_refresh);

    }

    private void BindData(String url, final Boolean show) {


        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String text = "[\"中秋大优惠\",\"七夕优惠劵大赠送\",\"中秋优惠劵大赠送\",\"国庆优惠劵大赠送\"]";

                List<String> data = JSON.parseArray(text, String.class);

                adapter = new ListViewAdapter(context, data);
                listView.setAdapter(adapter);

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
    }
}
