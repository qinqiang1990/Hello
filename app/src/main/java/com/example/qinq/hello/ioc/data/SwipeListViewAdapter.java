package com.example.qinq.hello.ioc.data;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinq.hello.R;

import java.util.List;
import java.util.Map;


public class SwipeListViewAdapter extends BaseAdapter {

    private class ViewHolder {

        TextView tv = null;
        Button btn = null;

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public Button getBtn() {
            return btn;
        }

        public void setBtn(Button btn) {
            this.btn = btn;
        }
    }

    private Context context;
    private List<String> data;
    private LayoutInflater inflater;
    private float downX;

    public SwipeListViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.swipe_list_item, null);

            TextView desc = (TextView) convertView.findViewById(R.id.swipe_fore_desc);
            Button del = (Button) convertView.findViewById(R.id.swipe_back_del);

            holder.setTv(desc);
            holder.setBtn(del);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(getItem(position).toString());

        final View finalConvertView = convertView;
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        float distince = downX - event.getX();
                        if (distince > 0) {
                            Animation translateAnimation = new TranslateAnimation(0, 0, distince, 0);
                            finalConvertView.startAnimation(translateAnimation);
                        }
                        break;

                }
                return false;
            }
        });

                /*Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
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
