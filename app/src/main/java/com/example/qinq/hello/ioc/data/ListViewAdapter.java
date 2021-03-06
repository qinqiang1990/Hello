package com.example.qinq.hello.ioc.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qinq.hello.R;

import java.util.List;


public class ListViewAdapter extends BaseAdapter {

    private class ViewHolder {

        TextView tv = null;

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }


    }

    private Context context;
    private List<String> data;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);

            TextView tv = (TextView) convertView.findViewById(R.id.si_textView);
            holder.setTv(tv);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(getItem(position).toString());

                /*
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
                v.startAnimation(animation);

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
                objectAnimator.setDuration(10000);
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator.start();


                ValueAnimator animation = ValueAnimator.ofInt(0, 200);
                animation.setDuration(700);
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        finalImage.getLayoutParams().height = value.intValue();
                        finalImage.requestLayout();
                        Log.i("qinq", value.toString());
                    }
                });

                animation.setInterpolator(new LinearInterpolator());
                animation.start();

                */

        return convertView;
    }
}
