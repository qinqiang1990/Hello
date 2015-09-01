package com.example.qinq.hello.ioc.data;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.qinq.hello.R;

import java.util.List;
import java.util.Map;


public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;

    public ListViewAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
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
        ImageView image = null;
        if (convertView == null) {
            convertView = listContainer.inflate(R.layout.list_item, null);
            image = (ImageView) convertView.findViewById(R.id.imageItem);
            convertView.setTag(image);
        } else {
            image = (ImageView) convertView.getTag();
        }

        image.setImageResource((Integer) listItems.get(position).get("image"));

        final ImageView finalImage = image;

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
                v.startAnimation(animation);*/

/*

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
                objectAnimator.setDuration(10000);
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator.start();
*/

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
            }
        });


        return convertView;
    }
}
