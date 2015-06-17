package com.chen.cxy.view.dynamic;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.chen.cxy.R;
import com.chen.cxy.utils.xListView.XListView;
import com.chen.cxy.utils.xListView.XListView.IXListViewListener;
import com.chen.cxy.view.dynamic.adapter.DynamicContentAdapter;
import com.chen.cxy.view.dynamic.entity.DynamicContentEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public class DynamicContentActivity extends AbActivity implements IXListViewListener,View.OnClickListener {

    XListView listView;
    DynamicContentAdapter adapter;
    List<DynamicContentEntity> list = new ArrayList<DynamicContentEntity>();
    private Handler mHandler;//模拟延时
    private Context context;

    String nickname ;//
    String address ;//
    String content ;//
    String likeCount ;//

    ImageView goback;
    TextView report;
    TextView pupop;

    View view;//弹出层显示的view
    PopupWindow popupWindow;//弹出层

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        this.setAbContentView(R.layout.activity_dynamic_content_reply);
        init();
        setData();
        listView = (XListView) this.findViewById(R.id.dynamic_content_listview);
        // 设置加载
        listView.setPullLoadEnable(true);
        adapter = new DynamicContentAdapter(this,list);
        listView.setAdapter(adapter);

        listView.setXListViewListener(this);

        mHandler = new Handler();

    }

    private void init(){
        goback = (ImageView)findViewById(R.id.adcr_goback);
        report = (TextView)findViewById(R.id.adcr_report);
        view = LayoutInflater.from(context).inflate(R.layout.del_popup, null);
        pupop =(TextView) view.findViewById(R.id.pupop);
        popupWindow = new PopupWindow(view, 100, WindowManager.LayoutParams.WRAP_CONTENT);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //刷新状态（必须刷新否则无效）
        popupWindow.update();
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); //网上找的我也不知道为什么

        goback.setOnClickListener(this);
        report.setOnClickListener(this);
        pupop.setOnClickListener(this);
    }

    private void setData(){
        //没有获取时间
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        nickname = bundle.getString("nickname_tv");
        address = bundle.getString("address_tv");
        content = bundle.getString("content_tv");
        likeCount = bundle.getString("like_count");

        ImageView i = new ImageView(this);
        ImageView j = new ImageView(this);
        DynamicContentEntity dce = null;

        i.setImageResource(R.mipmap.default_boy);
        j.setImageResource(R.mipmap.del_cat);
        dce = new DynamicContentEntity(i,j,nickname,address,content,null,likeCount);
        list.add(dce);

        i.setImageResource(R.mipmap.default_boy);
        dce = new DynamicContentEntity(i,"神蕴的分身","恭喜恭喜哈哈","12","05-31 18:55","3","蛮王 :","同喜同喜 哈哈哈哈哈哈",
                "05-31 18:57");
        list.add(dce);
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"辛德拉","凑凑热闹","0","05-31 18:55","3","剑圣 :","我也是来凑热闹的 哈哈哈哈哈哈",
                "05-31 18:57");
        list.add(dce);
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"提莫","@辛德拉:你想攻打我的要塞?","0","05-31 18:00");
        list.add(dce);
    }

    private void refresh(){
        list.clear();
        ImageView i = new ImageView(context);
        ImageView j = new ImageView(context);
        DynamicContentEntity dce = null;

        i.setImageResource(R.mipmap.default_boy);
        j.setImageResource(R.mipmap.del_cat);
        dce = new DynamicContentEntity(i,j,nickname,address,content,null,likeCount);
        list.add(dce);

        i.setImageResource(R.mipmap.default_boy);
        dce = new DynamicContentEntity(i,"阿凡提","哇塞哇塞哇塞哇塞哇塞","12","06-02 20:00","3","蛮王 :","同喜同喜 哈哈哈哈哈哈",
                "05-31 18:57");
        list.add(dce);
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"提莫","凑凑热闹","0","06-02 18:55","3","剑圣 :","我也是来凑热闹的 哈哈哈哈哈哈",
                "05-31 18:57");
        list.add(dce);
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"提莫","@辛德拉:你想攻打我的要塞?","0","05-31 18:00");
        list.add(dce);
        adapter = new DynamicContentAdapter(this,list);
        listView.setAdapter(adapter);
    }



    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
                onLoad();
            }
        }, 2000);
    }

    //加载更多
    @Override
    public void onLoadMore() {
        ImageView i = new ImageView(context);
        DynamicContentEntity dce = null;
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"莫甘娜","@辛德拉:你想攻打我的要塞?","0","05-31 18:00");
        list.add(dce);
        i.setImageResource(R.mipmap.default_girl);
        dce = new DynamicContentEntity(i,"泽拉斯","@莫甘娜:你想攻打我的要塞?","0","05-31 18:00");
        list.add(dce);

        adapter.notifyDataSetChanged();
        onLoad();
    }

    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime("刚刚");
    }

    @Override
    public void onClick(View v) {
        goback = (ImageView)findViewById(R.id.adcr_goback);
        report = (TextView)findViewById(R.id.adcr_report);;

        switch (v.getId()){
            case R.id.adcr_goback :
                this.finish();
                break;
            case R.id.adcr_report :

                popupWindow.showAsDropDown(v);
                break;
            case R.id.pupop :
                pupop.setBackgroundResource(R.color.pink);
                break;

        }
    }
}
