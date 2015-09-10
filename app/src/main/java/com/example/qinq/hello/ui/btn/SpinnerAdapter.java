package com.example.qinq.hello.ui.btn;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qinq.hello.R;

import java.util.List;
import java.util.Map;


public class SpinnerAdapter extends BaseAdapter {

    private int selectIndex;
    private Context context;
    private List<String> listItems;
    private LayoutInflater listContainer;

    public SpinnerAdapter(Context context, List<String> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = null;
        if (convertView == null) {
            convertView = listContainer.inflate(R.layout.list_item, null);
            tv = (TextView) convertView.findViewById(R.id.si_textView);
            convertView.setTag(tv);
        } else {
            tv = (TextView) convertView.getTag();
        }

        tv.setText(listItems.get(position));

        return convertView;
    }
}
