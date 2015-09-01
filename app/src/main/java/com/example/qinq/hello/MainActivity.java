package com.example.qinq.hello;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.qinq.hello.activity.BaseFragmentActivity;
import com.example.qinq.hello.fragment.IndexFragment;
import com.example.qinq.hello.fragment.OrderFragment;
import com.example.qinq.hello.fragment.PersonalFragment;
import com.example.qinq.hello.fragment.PreferentialFragment;
import com.example.qinq.hello.fragment.ShopFragment;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

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

    IndexFragment indexFragment;

    ShopFragment shopFragment;

    PreferentialFragment preferentialFragment;

    OrderFragment orderFragment;

    PersonalFragment personalFragment;

    Fragment curfragment;


    View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Fragment from = curfragment;
            Fragment to = null;
            switch (v.getId()) {
                case R.id.main_tabHomeBtn:
                    to = curfragment = indexFragment;

                    break;
                case R.id.main_tabShopBtn:
                    to = curfragment = shopFragment;

                    break;
                case R.id.main_tabPreferentialBtn:
                    to = curfragment = preferentialFragment;

                    break;
                case R.id.main_tabOrderBtn:
                    to = curfragment = orderFragment;

                    break;
                case R.id.main_tabPersonalBtn:
                    to = curfragment = personalFragment;

                    break;

            }
            switchFragment(from, to);
        }

    };


    @Override
    protected void initialize() {
        indexFragment = (IndexFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_indexFragment);
        shopFragment = (ShopFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_shopFragment);
        preferentialFragment = (PreferentialFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_preferentialFragment);
        orderFragment = (OrderFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_orderFragment);
        personalFragment = (PersonalFragment) this.getSupportFragmentManager().findFragmentById(R.id.main_personalFragment);


    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void addListener() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction().hide(shopFragment);
        transaction.hide(shopFragment);
        transaction.hide(preferentialFragment);
        transaction.hide(orderFragment);
        transaction.hide(personalFragment);
        transaction.commit();
        curfragment = indexFragment;

        tabHomeBtn.setOnClickListener(click);
        tabShopBtn.setOnClickListener(click);
        tabPreferentialBtn.setOnClickListener(click);
        tabOrderBtn.setOnClickListener(click);
        tabPersonalBtn.setOnClickListener(click);
    }


}
