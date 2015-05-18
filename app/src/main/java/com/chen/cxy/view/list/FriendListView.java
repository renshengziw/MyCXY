package com.chen.cxy.view.list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *  用于显示第一个Fragment 用户聊天信息ListView
 */
public class FriendListView extends ListView{

    //重构三个构造方法
    public FriendListView(Context context) {
        super(context);
    }

    public FriendListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FriendListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


}
