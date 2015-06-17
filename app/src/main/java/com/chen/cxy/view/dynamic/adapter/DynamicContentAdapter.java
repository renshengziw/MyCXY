package com.chen.cxy.view.dynamic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbStrUtil;
import com.chen.cxy.R;
import com.chen.cxy.common.Constant;
import com.chen.cxy.view.dynamic.entity.DynamicContentEntity;
import com.chen.cxy.view.home.entity.ChatMessageEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ZhangChen on 2015/5/29.
 * 动态内容显示/回复页面适配器
 */
public class DynamicContentAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    public String POSITION_CONTENT = "POSITION_CONTENT";
    public String POSITION_REPLY = "POSITION_REPLY";
    List<DynamicContentEntity> data;
    ViewHolder holder = null;

    //内容页
    ImageView portrait_iv;
    TextView nickname_tv;
    TextView address_tv;
    TextView content_tv;
    ImageView content_iv;
    ImageView love_pressed;
    TextView time_tv;

    //回复页
    ImageView dr_portrait_iv;
    TextView dr_nickname_tv;
    TextView dr_time_tv;
    TextView dr_content_tv;
    ImageView dr_love_pressed;
    TextView dr_like_count;






    public DynamicContentAdapter(Context context, List<DynamicContentEntity> data) {
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
    public DynamicContentEntity getItem(int position) {
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

        DynamicContentEntity dynamicContent = getItem(position);
        Log.i("TAG", "position"+position);

        inflater = LayoutInflater.from(this.context);

        if(position == 0){ //表示第一条记录 显示动态内容
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.dynamic_content_item,null);
            initContent(convertView);//初始化
            setContent(dynamicContent, position);//赋值
            convertView.setTag(holder);
        }else{
            if(convertView == null || !this.POSITION_REPLY.equals(((ViewHolder) convertView.getTag()).position)){
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.dynamic_reply_item1,null);
                initReply(convertView);//初始化
                //setContent(dynamicContent, position);//赋值
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            setContent(dynamicContent, position);//赋值

        }

        return convertView;
    }

    //初始化内容
    private void setContent(DynamicContentEntity dynamicContent, int position) {
        if(dynamicContent != null){
            holder.time.setText(dynamicContent.getTime()); ;
            holder.content.setText(dynamicContent.getContent());
            holder.portrait = dynamicContent.getPortrait();
            holder.nickname.setText(dynamicContent.getNickname());
            holder.likeCount.setText(dynamicContent.getLikeCount());
            holder.replyCount.setText(dynamicContent.getReplyCount());

            if(position == 0 ){//内容
                holder.contentImage = dynamicContent.getContentImage();
                holder.address.setText(dynamicContent.getAddress());
            }else{//评论
                holder.replyNickname.setText(dynamicContent.getReplyNickname());
                //拼接不同字体大小与颜色handleReplyContent()方法

                String replyCount = dynamicContent.getReplyCount();
                int count = 0;
                if(!AbStrUtil.isEmpty(replyCount)){
                    count = new Integer(replyCount); //如果有评论则评论数量!=null
                }
                if( replyCount!=null && count>0){
                    holder.replyLayout.setVisibility(View.VISIBLE);
                    holder.replyContent.setText(handleReplyContent(dynamicContent));
                    if(count>1){
                        holder.replyMore.setText("更多"+(count-1)+"条评论...");
                    }else{
                        holder.replyMore.setVisibility(View.GONE); //如果回复评论数量<1则不显示replyMore
                    }

                }else{
                    holder.replyLayout.setVisibility(View.GONE);//如果如果回复评论数量<1则不显示本页面
                }


            }
        }


    }

    /**
     * 评论回复页面拼接 回复内容与时间在同一个TextView中显示 字体颜色与大小不同
     * @param dynamicContent
     * @return
     */
    private SpannableStringBuilder handleReplyContent(DynamicContentEntity dynamicContent){

        String replyContent = dynamicContent.getReplyContent();
        String replyTime = dynamicContent.getReplyTime();
        int size = replyContent.length();
        SpannableStringBuilder builder=new SpannableStringBuilder();
        builder.append(replyContent);
        builder.append("            ");
        builder.append(replyTime);

        /**
         int flags：取值有如下四个
         Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
         Spannable.SPAN_EXCLUSIVE_INCLUSIVE	：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
         Spannable.SPAN_INCLUSIVE_EXCLUSIVE	：前面包括，后面不包括。
         Spannable.SPAN_INCLUSIVE_INCLUSIVE	：前后都包括。
         AbsoluteSizeSpan(30)30与xml中的sp大小不一致,需要根据页面调整
         */
        AbsoluteSizeSpan spanSize = new AbsoluteSizeSpan(30);
        builder.setSpan(spanSize,size,builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan spanColor = new ForegroundColorSpan(R.color.lightgray);
        builder.setSpan(spanColor,size,builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return builder;
    }

    //初始化内容显示
    private void initContent(View convertView){
        holder.nickname = (TextView) convertView.findViewById(R.id.nickname_tv);
        holder.portrait = (ImageView) convertView.findViewById(R.id.portrait_iv);
        holder.address = (TextView) convertView.findViewById(R.id.address_tv);
        holder.time = (TextView) convertView.findViewById(R.id.time_tv);
        holder.content = (TextView) convertView.findViewById(R.id.content_tv);
        holder.contentImage = (ImageView) convertView.findViewById(R.id.content_iv);
        holder.lovePressed = (ImageView) convertView.findViewById(R.id.love_pressed);
        holder.likeCount = (TextView) convertView.findViewById(R.id.like_count);
        holder.replyCount = (TextView) convertView.findViewById(R.id.dr_reply_count);
        holder.position=this.POSITION_CONTENT;//方位  content|reply
    }

    //初始化回复页面
    private void initReply(View convertView){
        holder.nickname = (TextView) convertView.findViewById(R.id.dr_nickname_tv);
        holder.portrait = (ImageView) convertView.findViewById(R.id.dr_portrait_iv);
        holder.time = (TextView) convertView.findViewById(R.id.dr_time_tv);
        holder.content = (TextView) convertView.findViewById(R.id.dr_content_tv);
        holder.lovePressed = (ImageView) convertView.findViewById(R.id.dr_love_pressed);
        holder.likeCount = (TextView) convertView.findViewById(R.id.dr_like_count);

        holder.replyCount = (TextView) convertView.findViewById(R.id.dr_reply_count);
        holder.replyNickname = (TextView) convertView.findViewById(R.id.dr_reply_nickname);
        holder.replyContent = (TextView) convertView.findViewById(R.id.dr_reply_content);
        holder.replyMore = (TextView) convertView.findViewById(R.id.dr_reply_more);
        holder.replyLayout = (LinearLayout) convertView.findViewById(R.id.dr_reply_layout);
        holder.position=this.POSITION_REPLY;//方位  content|reply

    }

    /*public void onClick(View v) {
        //点击用户头像跳转到用户详细信息界面
       *//* switch(v.getId()){
            case  R.id.friend_layout:
                Intent intent = new Intent(context, FriendChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName","神蕴");
                bundle.putString("friendName", nickname_tv.get[Text().toString());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//使用Context的startActivity方法的话，就需要开启一个新的task
                context.startActivity(intent);
                break;

        }*//*
    }*/


    private static class ViewHolder{
        String position;//方位  content|reply
        TextView time; //显示时间
        TextView content;//显示内容
        ImageView portrait;//显示的头像
        ImageView contentImage;//显示图片
        ImageView lovePressed;//喜欢
        TextView nickname;//评论用户昵称
        TextView address;//地址
        TextView likeCount;//喜欢
        TextView replyCount;//回复评论数量
        TextView replyNickname;//回复评论名称
        TextView replyContent;//回复评论内容
        TextView replyMore; //更多评论
        LinearLayout replyLayout;

    }
}
