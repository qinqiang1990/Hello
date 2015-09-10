package com.example.qinq.hello.ui.btn;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.qinq.hello.R;

public class SpinnerBtn extends Button {

    private PopupWindow selectPop;
    private ListView selectDataView;
    private SpinnerAdapter adapter;

    AdapterView.OnItemClickListener mItemClickListener;

    List<String> dataSource;

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public SpinnerBtn(Context context, AttributeSet attrs) {
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
     * 设置 监听器
     *
     * @param mItemClick
     */
    public void setOnItemClickListener(final AdapterView.OnItemClickListener mItemClick) {
        this.mItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                setSelectIndex(position);
                if (mItemClick != null) {
                    mItemClick.onItemClick(parent, view, position, id);
                }
                selectPop.dismiss();
            }
        };
    }


    /**
     * 显示pop
     *
     * @param context
     */
    public void showPop(Context context) {

        if (dataSource == null) {
            return;
        }

        if (selectPop == null) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.spinnerfull, null);
            selectDataView = (ListView) view
                    .findViewById(R.id.spinner_listView);

            if (adapter == null) {
                adapter = new SpinnerAdapter(getContext(), dataSource);
            }
            selectDataView.setAdapter(adapter);
            if (mItemClickListener != null)
                selectDataView.setOnItemClickListener(mItemClickListener);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPop.dismiss();
                }
            });


            if (adapter.getCount() > 8)
                selectDataView.getLayoutParams().height = context.getResources().getDisplayMetrics().heightPixels / 2;

            selectPop = new PopupWindow(view,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);

        }
        selectPop.setFocusable(true);
        selectPop.setOutsideTouchable(true);
        selectPop.setBackgroundDrawable(new BitmapDrawable());
        selectPop.showAsDropDown(this);


    }

    public void setSelectIndex(int selectIndex) {
        if (adapter != null) {
            adapter.setSelectIndex(selectIndex);
            adapter.notifyDataSetChanged();
        }
    }


}
