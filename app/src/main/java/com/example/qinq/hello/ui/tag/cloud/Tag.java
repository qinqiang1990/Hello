package com.example.qinq.hello.ui.tag.cloud;

/**
 * Created by Yasenia on 2015/8/24.
 */
public class Tag {

    private String text;
    private float locX, locY;

    public Tag(float locX, float locY, String text) {
        this.text = text;
        this.locX = locX;
        this.locY = locY;
    }

    public String getText() {
        return text;
    }

    public float getLocX() {
        return locX;
    }

    public float getLocY() {
        return locY;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }
}
