package cn.ecar.insurance.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by ding on 2017/12/20.
 */

public class Member {

    private Drawable icon;

    private String name;

    private String content;


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
