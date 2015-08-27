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

    private Context context;                        //运行上下文
    private List<Map<String, Object>> listItems;    //信息集合
    private LayoutInflater listContainer;           //视图容器

    public ListViewAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
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
        //自定义视图
        if (convertView == null) {
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item, null);
            //获取控件对象
            image = (ImageView) convertView.findViewById(R.id.imageItem);
            //设置控件集到convertView
            convertView.setTag(image);
        } else {
            image = (ImageView) convertView.getTag();
        }
        //设置文字和图片
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
