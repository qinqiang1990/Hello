package com.example.qinq.hello.ui.tag.cloud;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Iterator;

/**
 * Created by Yasenia on 2015/8/24.
 */
public class TagCloudView extends RelativeLayout {


    private TagCloud tagCloud;
    private Context mContext;

    public TagCloudView(Context context, TagCloud tagCloud) {

        super(context);
        this.mContext = context;
        this.tagCloud = tagCloud;

        addView();
    }


    private void addView() {
        Iterator it = tagCloud.iterator();
        while (it.hasNext()) {
            Tag tag = (Tag) it.next();
            TextView tv = new TextView(this.mContext);
            tv.setText(tag.getText());

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

            params.setMargins((int) tag.getLocX(), (int) tag.getLocY(), (int) tag.getLocX(), (int) tag.getLocY());
            tv.setLayoutParams(params);
            this.addView(tv);
        }
    }


}
