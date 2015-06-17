package com.chen.cxy.view.dynamic.entity;

import android.widget.ImageView;

/**
 * Created by Administrator on 2015/5/29.
 * 动态回复页面 adapter处理实体类
 */
public class DynamicContentEntity {

    ImageView portrait;
    ImageView contentImage;
    String nickname;
    String address;
    String content;
    String time;
    String likeCount;

    String replyCount;
    String replyNickname;
    String replyContent;//回复的评论内容
    String replyTime;//回复的评论时间



    public DynamicContentEntity() {

    }

    public DynamicContentEntity(ImageView portrait, String nickname, String content, String likeCount, String time, String replyCount, String replyNickname, String replyContent,String replyTime) {
        this.portrait = portrait;
        this.nickname = nickname;
        this.content = content;
        this.likeCount = likeCount;
        this.time = time;
        this.replyCount = replyCount;
        this.replyNickname = replyNickname;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
}

    public DynamicContentEntity(ImageView portrait, ImageView contentImage, String nickname, String address, String content, String time, String likeCount) {
        this.portrait = portrait;
        this.contentImage = contentImage;
        this.nickname = nickname;
        this.address = address;
        this.content = content;
        this.time = time;
        this.likeCount = likeCount;
    }

    public DynamicContentEntity(ImageView portrait, String nickname, String content,String likeCount,String time) {
        this.portrait = portrait;
        this.nickname = nickname;
        this.content = content;
        this.likeCount = likeCount;
        this.time = time;
    }

    public ImageView getPortrait() {
        return portrait;
    }

    public void setPortrait(ImageView portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageView getContentImage() {
        return contentImage;
    }

    public void setContentImage(ImageView contentImage) {
        this.contentImage = contentImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getReplyNickname() {
        return replyNickname;
    }

    public void setReplyNickname(String replyNickname) {
        this.replyNickname = replyNickname;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
