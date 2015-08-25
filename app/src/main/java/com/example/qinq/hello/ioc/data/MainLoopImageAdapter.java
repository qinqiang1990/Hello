package com.example.qinq.hello.ioc.data;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Yasenia on 2015/8/25.
 */
public class MainLoopImageAdapter extends PagerAdapter {

    private Context context;
    private int[] resData;

    public MainLoopImageAdapter(Context context, int[] resData) {
        this.context = context;
        this.resData = resData;
    }

    @Override
    public int getCount() {
        return this.resData.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(this.context);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(this.resData[position]);
        container.addView(imageView);
        return imageView;

    }
}
