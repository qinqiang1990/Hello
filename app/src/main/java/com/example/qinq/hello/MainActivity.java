package com.example.qinq.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.qinq.hello.ioc.ViewInjectUtils;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ui.tag.cloud.Tag;
import com.example.qinq.hello.ui.tag.cloud.TagCloud;
import com.example.qinq.hello.ui.tag.cloud.TagCloudView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

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
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        // setContentView(R.layout.activity_main);
        //ViewInjectUtils.inject(this);

        TagCloud tagCloud = createTags();

        TagCloudView mTagCloudView = new TagCloudView(this, tagCloud);
        setContentView(mTagCloudView);
        mTagCloudView.requestFocus();
        mTagCloudView.setFocusableInTouchMode(true);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
