package qinq.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.animation.AnimatorProxy;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


public class SwipeListViewAdapter extends BaseAdapter {

    private class ViewHolder {

        TextView tv = null;
        Button btn = null;

        Button front = null;
        Button back = null;

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
    /**
     * 滑动速度检测类
     */
    private VelocityTracker mVelocityTracker;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.qinq_swipe_list_item, null);

            TextView tv = (TextView) convertView.findViewById(R.id.id_text);
            Button del = (Button) convertView.findViewById(R.id.id_remove);

            holder.setTv(tv);
            holder.setBtn(del);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv.setText(getItem(position).toString());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        final View front = convertView.findViewById(R.id.id_front);
        front.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        float deltaX = event.getX() - downX;

                        TranslateAnimation animation = new TranslateAnimation(0.0f, deltaX, 0.0f, 0.0f);
                        animation.setDuration(1000);
                        animation.setFillAfter(true);

                        front.startAnimation(animation);

                        break;
                }

                return true;
            }
        });

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
