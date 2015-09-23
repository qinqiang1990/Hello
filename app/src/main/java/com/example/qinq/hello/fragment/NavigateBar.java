package com.example.qinq.hello.fragment;

import android.annotation.TargetApi;
import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qinq.hello.R;
import com.example.qinq.hello.ioc.view.ContentView;

/**
 * Created by qinqiang on 2015/8/27.
 */

@ContentView(R.layout.navigate)
public class NavigateBar extends BaseFragment implements View.OnClickListener {

    private TextView tv;

    private Button leftIndicatorBtn;

    private Button rightIndicatorBtn;

    @Override
    protected void initialize() {
        tv = (TextView) rootView.findViewById(R.id.nav_title);
    }


    public void addBackBtn(View.OnClickListener listener) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.navigate_back, null);
        ImageButton backIndicatorBtn = (ImageButton) view.findViewById(R.id.nav_indicator_back);
        backIndicatorBtn.setOnClickListener(listener);

        ((ViewGroup) rootView).addView(view);

/*      Application app = getActivity().getApplication();
        getActivity().finish();*/

    }

    public void setTitle(String title) {
        tv.setText(title);
    }

    public void addSwitchIndicatorView(String leftText, String rightText) {
        View indicatorView = LayoutInflater.from(getActivity()).inflate(R.layout.nav_switch_indicator, null);
        leftIndicatorBtn = (Button) indicatorView
                .findViewById(R.id.nav_indicator_leftBtn);
        rightIndicatorBtn = (Button) indicatorView
                .findViewById(R.id.nav_indicator_rightBtn);
        leftIndicatorBtn.setText(leftText);
        rightIndicatorBtn.setText(rightText);
        leftIndicatorBtn.setSelected(true);
        rightIndicatorBtn.setSelected(false);

        leftIndicatorBtn.setOnClickListener(this);
        rightIndicatorBtn.setOnClickListener(this);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);

        ((ViewGroup) rootView).addView(indicatorView, rlp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_indicator_leftBtn:
                leftIndicatorBtn.setSelected(true);
                rightIndicatorBtn.setSelected(false);
                break;
            case R.id.nav_indicator_rightBtn:
                leftIndicatorBtn.setSelected(false);
                rightIndicatorBtn.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {

    }


}
