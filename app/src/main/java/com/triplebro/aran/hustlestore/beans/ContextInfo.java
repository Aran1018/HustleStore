package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/1/1.
 */


public class ContextInfo {

    String userHead;
    String userName;
    String content_img;
    String user_id;
    String user_introduction;
    String publish_content;
    String publish_time;
    String publish_id;

    public String getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(String publish_id) {
        this.publish_id = publish_id;
    }

    public ContextInfo() {
    }

    public ContextInfo(String userHead, String userName, String content_img, String user_id, String publish_content,String publish_id) {
        this.userHead = userHead;
        this.userName = userName;
        this.content_img = content_img;
        this.user_id = user_id;
        this.publish_content = publish_content;
        this.publish_id = publish_id;
    }

    public String getUser_introduction() {
        return user_introduction;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent_img() {
        return content_img;
    }

    public void setContent_img(String content_img) {
        this.content_img = content_img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPublish_content() {
        return publish_content;
    }

    public void setPublish_content(String publish_content) {
        this.publish_content = publish_content;
    }

    @Override
    public String toString() {
        return "ContextInfo{" +
                "userHead='" + userHead + '\'' +
                ", userName='" + userName + '\'' +
                ", content_img='" + content_img + '\'' +
                ", user_id='" + user_id + '\'' +
                ", publish_content='" + publish_content + '\'' +
                '}';
    }
}
