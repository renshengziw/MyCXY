package com.chen.cxy.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.chen.cxy.R;
import com.chen.cxy.common.Constant;
import com.chen.cxy.view.home.adapter.FriendChatAdapter;
import com.chen.cxy.view.home.entity.ChatMessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/19.
 *
 */
public class FriendChatActivity extends AbActivity{
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float x3 = 0;
    float y1 = 0;
    float y2 = 0;
    float y3 = 0;

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
        list.add(new ChatMessageEntity(Constant.MESSAGE_FROM, "2015-5-21 10:06:20", "......", image));

        return list;
    }


    /**
     *
     * dispatchTouchEvent优先级别高于onTouchEvent
     * ListView的onTouchEvent会被占用
    */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN :
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP :
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();

             /*
            当如果滑动是向右滑动,并且向下移动距离不超过200 向上不超过50 ,手指离开屏幕时finish    此Activity
            横向最左边是0
            竖向最上部是0
             */
                if(x2 > x1  && ((y2-y1 <=200 && y2-y1 >=-50) || (y1-y2 <=200 && y1-y2 >=-50))) {//从左向右
                    Log.i("cxy","x2"+x2+" x1="+x1+" y2="+y2+" y1="+y1);
                    this.finish();
                    //return true;
                }
            /*if(y1 - y2 > 50) {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }*/
                break;
            case MotionEvent.ACTION_MOVE :
                x3 = event.getX();
                y3 = event.getY();
            /*
            当向右滑动,并且向下移动距离不超过200 向上不超过50 则拦截touch事件不传递给下一集view(本页面为ListView)
            横向最左边是0
            竖向最上部是0
             */
                if(x3 > x1  && ((y3-y1 <=200 && y3-y1 >=-50) || (y1-y3 <=200 && y1-y3 >=-50))){
                    return true;//return true表示拦截事件不向一下层传递
                }
                break;
        }

        return super.dispatchTouchEvent(event);


    }
}
