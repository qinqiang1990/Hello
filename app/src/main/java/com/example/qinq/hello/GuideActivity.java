package com.example.qinq.hello;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.qinq.hello.ioc.BaseActivity;
import com.example.qinq.hello.ioc.data.MainLoopImageAdapter;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

@ContentView(R.layout.guide)
public class GuideActivity extends BaseActivity {


    @ViewInject(R.id.guide_experienceBtn)
    Button experienceBtn;

    @ViewInject(R.id.guide_viewPager)
    ViewPager viewPager;


    @Override
    protected void initialize() {

        MainLoopImageAdapter adapter = new MainLoopImageAdapter(this, new int[]{});
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void addListener() {

        experienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
