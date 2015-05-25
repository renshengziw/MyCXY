package com.chen.cxy.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.chen.cxy.R;
import com.chen.cxy.view.home.FriendChatActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhangChen on 2015/5/14.
 * 首页-聊天页面适配器
 */
public class FriendAdapter extends SimpleAdapter implements View.OnClickListener{
    Context context;
    LayoutInflater inflater;
    TextView count_tv;
    LinearLayout friendLayout;
    TextView nickname_tv;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public FriendAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(this.context);

        if(convertView == null){
            convertView = super.getView(position, convertView, parent);
        }

        //初始化
        init(convertView);

        String count = count_tv.getText().toString();
        if(count.equals("0")){
            count_tv.setVisibility(View.GONE); //如果未读信息等于0则不显示未读信息数量
        }



        return convertView;
    }

    private void init(View convertView){
        count_tv = (TextView) convertView.findViewById(R.id.count_tv);
        nickname_tv = (TextView) convertView.findViewById(R.id.nickname_tv);
        friendLayout = (LinearLayout) convertView.findViewById(R.id.friend_layout);
        Log.i("TAG", "count_tv=" + count_tv.getText().toString() + " / nickname_iv="+nickname_tv.getText().toString());

        friendLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case  R.id.friend_layout:
                //使用v获取的才是对应item中的内容
                TextView tv = (TextView) v.findViewById(R.id.nickname_tv);
                Intent intent = new Intent(context, FriendChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName","神蕴");
                bundle.putString("friendName", tv.getText().toString());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//使用Context的startActivity方法的话，就需要开启一个新的task
                context.startActivity(intent);
                break;

        }
    }
}
