package com.example.qinq.hello.ui.tag.cloud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yasenia on 2015/8/24.
 */
public class TagCloud implements Iterable<Object> {

    private List<Tag> tagCloud = new ArrayList<Tag>();

    public TagCloud() {
    }

    public void add(Tag tag) {
        tagCloud.add(tag);
    }

    @Override
    public Iterator iterator() {
        return tagCloud.iterator();
    }

}
