package com.chen.cxy.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.chen.cxy.R;
import com.chen.cxy.common.Constant;
import com.chen.cxy.view.adapter.FriendAdapter;
import com.chen.cxy.view.adapter.FriendChatAdapter;
import com.chen.cxy.view.home.entity.ChatMessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/19.
 *
 */
public class FriendChatActivity extends AbActivity{


    private AbTitleBar mAbTitleBar = null;//标题栏
    private ListView listView;

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

        listView = (ListView) findViewById(R.id.chat_list);

        FriendChatAdapter adapter = new FriendChatAdapter(this,getData());
        listView.setAdapter(adapter);
    }

    //获取数据目前是固定数据
    private List<ChatMessageEntity> getData(){

        List list = new ArrayList();
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.default_boy);
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 10:00:00", "你好", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 10:03:00", "你在么?", image));
        image.setImageResource(R.mipmap.default_girl);
        list.add(new ChatMessageEntity(Constant.MESSAGE_FROM, "2015-5-21 10:03:20", "在的", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_FROM, "2015-5-21 10:04:00", "有什么事吗?", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_FROM, "2015-5-21 10:10:00", "人嘞?", image));
        image.setImageResource(R.mipmap.default_boy);
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 11:00:00", "在了", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 11:00:02", "刚刚在忙", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 11:00:10", "你是男的女的?", image));
        list.add(new ChatMessageEntity(Constant.MESSAGE_TO, "2015-5-21 11:00:12", "你认识刘荣不?", image));
        image.setImageResource(R.mipmap.default_girl);
        list.add(new ChatMessageEntity(Constant.MESSAGE_FROM,"2015-5-21 10:06:20","......",image));

        return list;
    }
}
