package com.example.qinq.hello;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qinq.hello.activity.BaseFragmentActivity;
import com.example.qinq.hello.fragment.BaseFragment;
import com.example.qinq.hello.fragment.HomeFragment;
import com.example.qinq.hello.fragment.NavigateBar;
import com.example.qinq.hello.fragment.OrderFragment;
import com.example.qinq.hello.fragment.PersonalFragment;
import com.example.qinq.hello.fragment.PreferentialFragment;
import com.example.qinq.hello.fragment.ShopFragment;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import static android.widget.Toast.LENGTH_LONG;

@ContentView(R.layout.main)
public class MainActivity extends BaseFragmentActivity {


    @ViewInject(value = R.id.main_tabHomeBtn)
    Button tabHomeBtn;

    @ViewInject(value = R.id.main_tabShopBtn)
    Button tabShopBtn;

    @ViewInject(value = R.id.main_tabPreferentialBtn)
    Button tabPreferentialBtn;

    @ViewInject(value = R.id.main_tabOrderBtn)
    Button tabOrderBtn;

    @ViewInject(value = R.id.main_tabPersonalBtn)
    Button tabPersonalBtn;

    HomeFragment homeFragment;

    ShopFragment shopFragment;

    PreferentialFragment preferentialFragment;

    OrderFragment orderFragment;

    PersonalFragment personalFragment;

    FragmentRelation currFragmentRelation;

    NavigateBar navBar;

    View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            FragmentRelation from = currFragmentRelation;
            FragmentRelation to = null;
            switch (v.getId()) {
                case R.id.main_tabHomeBtn:
                    to = currFragmentRelation = FragmentRelation.HOME;
                    break;
                case R.id.main_tabShopBtn:
                    to = currFragmentRelation = FragmentRelation.SHOP;
                    break;
                case R.id.main_tabPreferentialBtn:
                    to = currFragmentRelation = FragmentRelation.PREFERENTIAL;
                    break;
                case R.id.main_tabOrderBtn:
                    to = currFragmentRelation = FragmentRelation.ORDER;
                    break;
                case R.id.main_tabPersonalBtn:
                    to = currFragmentRelation = FragmentRelation.PERSONAL;
                    break;

            }
            if (from != to) {
                switchFragment(from.fragment, to.fragment);
                from.unSelect(getResources());
                to.select(getResources());
                to.fragment.initNavBar(navBar);
            }
        }

    };


    @Override
    protected void initialize() {
        homeFragment = (HomeFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_homeFragment);
        shopFragment = (ShopFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_shopFragment);
        preferentialFragment = (PreferentialFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_preferentialFragment);
        orderFragment = (OrderFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_orderFragment);
        personalFragment = (PersonalFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_personalFragment);

        navBar = (NavigateBar) this.getSupportFragmentManager().findFragmentById(R.id.main_navBar);


        FragmentRelation.HOME.setRelation(homeFragment, tabHomeBtn, R.mipmap.tab_home_pre, R.mipmap.tab_home_nor);

        FragmentRelation.SHOP.setRelation(shopFragment, tabShopBtn, R.mipmap.tab_shop_pre, R.mipmap.tab_shop_nor);

        FragmentRelation.PREFERENTIAL.setRelation(preferentialFragment, tabPreferentialBtn, R.mipmap.tab_preferential_pre, R.mipmap.tab_preferential_nor);

        FragmentRelation.ORDER.setRelation(orderFragment, tabOrderBtn, R.mipmap.tab_order_pre, R.mipmap.tab_order_nor);

        FragmentRelation.PERSONAL.setRelation(personalFragment, tabPersonalBtn, R.mipmap.tab_personal_pre, R.mipmap.tab_personal_nor);


    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction().hide(shopFragment);
        transaction.hide(FragmentRelation.PREFERENTIAL.fragment);
        transaction.hide(FragmentRelation.SHOP.fragment);
        transaction.hide(FragmentRelation.ORDER.fragment);
        transaction.hide(FragmentRelation.PERSONAL.fragment);
        transaction.commit();
        currFragmentRelation = FragmentRelation.HOME;

        tabHomeBtn.setOnClickListener(click);
        tabShopBtn.setOnClickListener(click);
        tabPreferentialBtn.setOnClickListener(click);
        tabOrderBtn.setOnClickListener(click);
        tabPersonalBtn.setOnClickListener(click);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String name) {
        if (currFragmentRelation != FragmentRelation.HOME) {
            switchFragment(currFragmentRelation.fragment, FragmentRelation.HOME.fragment);
            currFragmentRelation.unSelect(getResources());
            FragmentRelation.HOME.select(getResources());
            FragmentRelation.HOME.fragment.initNavBar(navBar);

            currFragmentRelation = FragmentRelation.HOME;
        }
    }

}


enum FragmentRelation {
    HOME, SHOP, PREFERENTIAL, ORDER, PERSONAL;

    BaseFragment fragment;
    Button button;
    int tagUnSelectRes, tagSelectRes;

    void setRelation(BaseFragment fragment, Button button, int tagSelectRes, int tagUnSelectRes) {
        this.fragment = fragment;
        this.button = button;
        this.tagUnSelectRes = tagUnSelectRes;
        this.tagSelectRes = tagSelectRes;
    }

    void select(Resources res) {

        button.setTextColor(Color.parseColor("#f88705"));
        Drawable drawable = res.getDrawable(tagSelectRes);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        button.setCompoundDrawables(null, drawable, null, null);
    }

    void unSelect(Resources res) {

        button.setTextColor(Color.parseColor("#333333"));
        Drawable drawable = res.getDrawable(tagUnSelectRes);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        button.setCompoundDrawables(null, drawable, null, null);
    }
}
