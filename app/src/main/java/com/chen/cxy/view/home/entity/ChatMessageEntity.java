package com.chen.cxy.view.home.entity;

import android.widget.ImageView;

/**
 * Created by Administrator on 2015/5/20.
 * 消息实体类
 */
public class ChatMessageEntity {

    private int direction;//消息方向发送或接收 从Constant中的MESSAGE_TO 1发送/MESSAGE_FROM0接收选值
    private String time;
    private String content;//消息内容
    private ImageView portrait;

    /**
     * @param direction 从Constant中的messaga_send/messaga_receive选值
     * @param content
     */
    public ChatMessageEntity(int direction,String time, String content,ImageView portrait) {
        this.direction = direction;
        this.time = time;
        this.content = content;
        this.portrait = portrait;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ImageView getPortrait() {
        return portrait;
    }

    public void setPortrait(ImageView portrait) {
        this.portrait = portrait;
    }
}
