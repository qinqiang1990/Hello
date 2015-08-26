package com.example.qinq.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.qinq.hello.ioc.BaseActivity;
import com.example.qinq.hello.ioc.ViewInjectUtils;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ui.tag.cloud.Tag;
import com.example.qinq.hello.ui.tag.cloud.TagCloud;
import com.example.qinq.hello.ui.tag.cloud.TagCloudView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private TagCloud createTags() {
        TagCloud tagCloud = new TagCloud();
        tagCloud.add(new Tag(600, 700, "http://www.google.com"));
        tagCloud.add(new Tag(300, 600, "www.yahoo.com"));
        tagCloud.add(new Tag(200, 800, "www.cnn.com"));
        return tagCloud;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        /*
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        TagCloud tagCloud = createTags();

        TagCloudView mTagCloudView = new TagCloudView(this, tagCloud);
        setContentView(mTagCloudView);
        mTagCloudView.requestFocus();
        mTagCloudView.setFocusableInTouchMode(true);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);*/
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void addListener() {

    }
}
