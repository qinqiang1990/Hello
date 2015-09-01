package com.example.qinq.hello.ioc.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qinq.hello.R;

import java.util.List;
import java.util.Map;


public class GridViewAdapter extends BaseAdapter {


    private class ViewHolder {

        TextView tv = null;
        ImageView img = null;

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public ImageView getImg() {
            return img;
        }

        public void setImg(ImageView img) {
            this.img = img;
        }

    }

    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> items;

    public GridViewAdapter(Context context, List<Map<String, Object>> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {


            convertView = inflater.inflate(R.layout.gridview_item, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.gridview_text);
            holder.img = (ImageView) convertView.findViewById(R.id.gridview_img);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.tv.setText(items.get(position).get("text").toString());
        //holder.img.setImageResource();

        return convertView;
    }
}
