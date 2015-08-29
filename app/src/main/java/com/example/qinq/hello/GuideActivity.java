package com.example.qinq.hello;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.qinq.hello.activity.BaseActivity;
import com.example.qinq.hello.ioc.data.MainLoopImageAdapter;
import com.example.qinq.hello.ioc.view.ClickMethod;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

@ContentView(R.layout.guide)
public class GuideActivity extends BaseActivity {

    private static final int SCROLL_WHAT = 0;
    private static final long interval = 2000;

    @ViewInject(R.id.guide_experienceBtn)
    Button experienceBtn;

    @ViewInject(R.id.guide_viewPager)
    ViewPager viewPager;

    MyHandler handler;

    MainLoopImageAdapter adapter;

    @ClickMethod(id = {R.id.guide_experienceBtn})
    public void onClick(View v) {
        stopAutoScroll();
    }


    @Override
    protected void initialize() {

        adapter = new MainLoopImageAdapter(this, new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4});
        viewPager.setAdapter(adapter);

        handler = new MyHandler();
        startAutoScroll(interval);
    }

    @Override
    protected void addListener() {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    experienceBtn.setVisibility(View.VISIBLE);
                } else {
                    experienceBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private class MyHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT:
                    int currentItem = viewPager.getCurrentItem();
                    int totalCount = adapter.getCount();
                    currentItem++;
                    if (currentItem >= totalCount) {
                        currentItem = 0;
                    }
                    viewPager.setCurrentItem(currentItem, true);
                    sendScrollMessage(interval);
                    break;
                default:
                    break;
            }
        }

    }

    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        handler.removeMessages(SCROLL_WHAT);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }

    public void startAutoScroll(long delayTimeInMills) {
        sendScrollMessage(delayTimeInMills);
    }


    public void stopAutoScroll() {
        handler.removeMessages(SCROLL_WHAT);
    }
}
