package com.chen.cxy.view.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.cxy.R;
import com.chen.cxy.common.Constant;
import com.chen.cxy.view.home.entity.ChatMessageEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ZhangChen on 2015/5/14.
 * 首页-聊天页面适配器
 */
public class FriendChatAdapter extends BaseAdapter implements View.OnClickListener{
    Context context;
    LayoutInflater inflater;
    TextView time_tv;
    TextView content_tv;
    ImageView portrait_iv;
    List<ChatMessageEntity> data;
    ViewHolder holder = null;


    public FriendChatAdapter(Context context, List<ChatMessageEntity> data) {
        super();
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(data != null){
            return data.size();
        }else{
            return 0;
        }

    }

    @Override
    public ChatMessageEntity getItem(int position) {
        if(data != null){
            return data.get(position);
        }else{
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatMessageEntity message = getItem(position);

        inflater = LayoutInflater.from(this.context);

        //如果convertView==null 或者 convertView配置是接收消息 而现在需要配置的是发送消息则需要重新创建convertView
        if(convertView == null || ((ViewHolder) convertView.getTag()).direction != message.getDirection()){
            holder = new ViewHolder();

            if(Constant.MESSAGE_FROM == message.getDirection()){
                holder.direction = Constant.MESSAGE_FROM; //接收消息
                convertView = inflater.inflate(R.layout.friend_chat_from_item,null);
            }else{
                holder.direction = Constant.MESSAGE_TO;//发送消息
                convertView = inflater.inflate(R.layout.friend_chat_to_item,null);
            }

            //初始化
            init(convertView);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        if(message != null){
            holder.time.setText(message.getTime());
            holder.content.setText(message.getContent());
            holder.portrait = message.getPortrait();
        }

        return convertView;
    }

    private void init(View convertView){
        holder.time = (TextView) convertView.findViewById(R.id.fc_time_tv);
        holder.content = (TextView) convertView.findViewById(R.id.fc_content_tv);
        holder.portrait = (ImageView) convertView.findViewById(R.id.fc_portrait_iv);

    }

    @Override
    public void onClick(View v) {
        //点击用户头像跳转到用户详细信息界面
       /* switch(v.getId()){
            case  R.id.friend_layout:
                Intent intent = new Intent(context, FriendChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName","神蕴");
                bundle.putString("friendName", nickname_tv.get[Text().toString());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//使用Context的startActivity方法的话，就需要开启一个新的task
                context.startActivity(intent);
                break;

        }*/
    }

    //TODO:等待处理时间显示
    private String computingTime(String time){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date theTime = sdf.parse(time);
            /*if(date.compareTo(theTime)){

            }*/
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }




    private static class ViewHolder{
        int direction;//方向
        TextView time; //显示时间
        TextView content;//显示内容
        ImageView portrait;//显示的头像
    }
}
