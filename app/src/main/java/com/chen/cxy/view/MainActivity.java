package com.chen.cxy.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ab.activity.AbActivity;
import com.chen.cxy.R;
import com.chen.cxy.view.fragment.dynamic.DynamicFragment;
import com.chen.cxy.view.fragment.home.FriendFragment;
import com.chen.cxy.view.fragment.issue.IssueFragment;


public class MainActivity extends AbActivity implements View.OnClickListener{


    private LinearLayout homeLayout;
    private LinearLayout dynamicLayout;
    private LinearLayout issueLayout;
    private LinearLayout discoveryLayout;
    private LinearLayout userLayout;

    //ͼƬ��ť
    private ImageButton homeButton;//主页目前是聊天模块
    private ImageButton dynamicButton;
    private ImageButton discoveryButton;
    private ImageButton issueButton;
    private ImageButton userButton;

    //������ʾ
    private TextView home_tv;
    private TextView dynamic_tv;
    private TextView discovery_tv;
    private TextView user_tv;

    private DynamicFragment dynamicFragment;
    private FriendFragment friendFragment;
    private IssueFragment issueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        selected(1);


    }

    /*初始化*/
    private void init(){
        homeLayout = (LinearLayout) findViewById(R.id.id_home);
        dynamicLayout = (LinearLayout) findViewById(R.id.id_dynamic);
        issueLayout = (LinearLayout) findViewById(R.id.id_issue);
        discoveryLayout = (LinearLayout) findViewById(R.id.id_discovery);
        userLayout = (LinearLayout) findViewById(R.id.id_user);

        homeButton = (ImageButton) findViewById(R.id.id_home_img);//��ҳ Ŀǰ��ҳ���û�
        dynamicButton = (ImageButton) findViewById(R.id.id_dynamic_img);
        discoveryButton = (ImageButton) findViewById(R.id.id_discovery_img);
        issueButton = (ImageButton) findViewById(R.id.id_issue_img);
        userButton = (ImageButton) findViewById(R.id.id_user_img);

        home_tv = (TextView) findViewById(R.id.id_home_tv);
        dynamic_tv = (TextView) findViewById(R.id.id_dynamic_tv);
        discovery_tv = (TextView) findViewById(R.id.id_discovery_tv);
        user_tv = (TextView) findViewById(R.id.id_user_tv);

        homeLayout.setOnClickListener(this);
        dynamicLayout.setOnClickListener(this);
        issueLayout.setOnClickListener(this);
        discoveryLayout.setOnClickListener(this);
        userLayout.setOnClickListener(this);
    }



    private void selected(int id){

        //使用android.support.v4.app.FragmentManager 需要继承FragmentActivity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        //隐藏所有Fragment
        hideFragment(beginTransaction);
        //第一次使用add 当Fragment不为Null时使用show显示
        switch (id) {
            case 1:
                if(friendFragment != null){
                    beginTransaction.show(friendFragment);
                }else{
                    friendFragment = new FriendFragment();
                    beginTransaction.add(R.id.main_frame, friendFragment);
                }
                homeButton.setImageResource(R.mipmap.friend_pressed);
                home_tv.setTextColor(this.getResources().getColor(R.color.maincion));
                break;
            case 2:
                if(dynamicFragment != null){
                    beginTransaction.show(dynamicFragment);
                }else{
                    dynamicFragment = new DynamicFragment();
                    beginTransaction.add(R.id.main_frame, dynamicFragment);
                }
                dynamicButton.setImageResource(R.mipmap.dynamic_pressed);
                dynamic_tv.setTextColor(this.getResources().getColor(R.color.maincion));
                break;
            case 3:
                discoveryButton.setImageResource(R.mipmap.discovery_pressed);
                discovery_tv.setTextColor(this.getResources().getColor(R.color.maincion));
                break;
            case 4:
                userButton.setImageResource(R.mipmap.user_pressed);
                user_tv.setTextColor(this.getResources().getColor(R.color.maincion));
                break;
            case 5:
                if(issueFragment == null){
                    issueFragment = new IssueFragment(this);
                    beginTransaction.add(R.id.main_frame, issueFragment);
                }else{
                    beginTransaction.show(issueFragment);

                }

            default:
                break;
        }

        beginTransaction.commit();
    }


    /**
     * 切换Fragment时先隐藏所有的Fragment
     * @param beginTransaction
     */
    private void hideFragment(FragmentTransaction beginTransaction) {
        if(friendFragment != null){
            beginTransaction.hide(friendFragment);
        }

        if(dynamicFragment != null){
            beginTransaction.hide(dynamicFragment);
        }

        if(issueFragment != null){
            beginTransaction.hide(issueFragment);
        }


    }


    @Override
    public void onClick(View v) {
        resetImg() ;
        switch (v.getId()){
            case  R.id.id_home:
                selected(1); //选择首页(聊天)
                break;
            case  R.id.id_dynamic:
                selected(2);//选择动态
                break;
            case  R.id.id_discovery:
                selected(3); //发现
                break;
            case  R.id.id_user:
                selected(4);//选择用户信息
                break;
            case R.id.id_issue:
                selected(5);

        }
    }

    /**
     * 每次点击后重置ImageButton的图片为对应未点击状态
     */
    private void resetImg() {
        homeButton.setImageResource(R.mipmap.friend_normal);
        dynamicButton.setImageResource(R.mipmap.dynamic_normal);
        discoveryButton.setImageResource(R.mipmap.discovery_normal);
        userButton.setImageResource(R.mipmap.user_normal);
        home_tv.setTextColor(this.getResources().getColor(R.color.gray));
        dynamic_tv.setTextColor(this.getResources().getColor(R.color.gray));
        discovery_tv.setTextColor(this.getResources().getColor(R.color.gray));
        user_tv.setTextColor(this.getResources().getColor(R.color.gray));
    }
}
