package com.chen.cxy.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.chen.cxy.R;

/**
 * Created by Administrator on 2015/5/19.
 *
 */
public class FriendChatActivity extends AbActivity{


    private AbTitleBar mAbTitleBar = null;//标题栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_friend_chat);

        init();

    }

    //初始化
    private void init(){

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");//app用户名
        String friendName = bundle.getString("friendName");//好友名称

        mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText(friendName);
        mAbTitleBar.setLogo(R.drawable.del_button_selector_back);
        mAbTitleBar.setTitleBarBackground(R.mipmap.del_top_bg);
        mAbTitleBar.setTitleTextMargin(20, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.mipmap.del_line);
        //标题点击事件
        mAbTitleBar.setLogoOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AbToastUtil.showToast(FriendChatActivity.this, "点了返回哦");
                finish();
            }
        });


    }
}
