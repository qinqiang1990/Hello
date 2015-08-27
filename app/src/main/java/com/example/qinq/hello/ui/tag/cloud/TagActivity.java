package com.example.qinq.hello.ui.tag.cloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.qinq.hello.R;

public class TagActivity extends Activity {


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

        //android:theme="@style/MyCustom">
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        TagCloud tagCloud = createTags();
        TagCloudView mTagCloudView = new TagCloudView(this, tagCloud);
        setContentView(mTagCloudView);
        mTagCloudView.requestFocus();
        mTagCloudView.setFocusableInTouchMode(true);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

    }


}
