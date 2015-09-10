package com.example.qinq.hello.ui.btn;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.qinq.hello.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandListBtn extends Button {

    private PopupWindow selectPop;
    private ListView selectDataView;
    private ListView secondSelectDataView;
    private ExpandListAdapter adapter;
    private ExpandListAdapter childadpter;

    private AdapterView.OnItemClickListener mItemClickListener;

    private List<String> dataSource;


    /**
     * 设置 监听器
     *
     * @param mItemClick
     */
    public void setOnItemClickListener(final AdapterView.OnItemClickListener mItemClick) {
        this.mItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                setSelectIndex(position);
                if (mItemClick != null) {
                    mItemClick.onItemClick(parent, view, position, id);
                }
                selectPop.dismiss();
            }
        };
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public ExpandListBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                showPop(getContext());
            }
        });

    }


    /**
     * 显示pop
     *
     * @param context
     */
    public void showPop(Context context) {
        setPressed(false);
        if (dataSource == null) {
            return;
        }
        if (selectPop == null) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.expandlist, null);
            selectDataView = (ListView) view.findViewById(R.id.firstListView);
            secondSelectDataView = (ListView) view.findViewById(R.id.secondListView);
            if (adapter == null) {
                adapter = new ExpandListAdapter(getContext(), dataSource);
            }
            selectDataView.setAdapter(adapter);
            selectDataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (childadpter == null) {
                        childadpter = new ExpandListAdapter(getContext(), dataSource);
                        secondSelectDataView.setAdapter(childadpter);
                        if (childadpter.getCount() > 8)
                            secondSelectDataView.getLayoutParams().height = getContext().getResources().getDisplayMetrics().heightPixels / 2;

                    } else {
                        List<String> data = new ArrayList<String>();
                        for (int i = 0; i < position; i++) {
                            data.add(dataSource.get(i));
                        }
                        childadpter.setData(data);
                        childadpter.notifyDataSetChanged();
                    }
                }
            });

            if (mItemClickListener != null)
                secondSelectDataView.setOnItemClickListener(mItemClickListener);

            if (adapter.getCount() > 8)
                selectDataView.getLayoutParams().height = context
                        .getResources().getDisplayMetrics().heightPixels / 2;

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPop.dismiss();
                }
            });

            selectPop = new PopupWindow(view,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
        }

        selectPop.setFocusable(true);
        selectPop.setOutsideTouchable(true);
        selectPop.setBackgroundDrawable(new BitmapDrawable());
        selectPop.showAsDropDown(this);

    }

    public void setSelectIndex(int index) {
        if (adapter != null) {
            adapter.setSelectIndex(index);
            adapter.notifyDataSetChanged();
        }


    }

}
