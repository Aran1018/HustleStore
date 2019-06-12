package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/8.
 */


public class CommentInfo {
    String user_Head;
    String user_name;
    String comments_content;
    String comments_date;
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public CommentInfo() {
    }

    public String getUser_Head() {
        return user_Head;
    }

    public void setUser_Head(String user_Head) {
        this.user_Head = user_Head;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComments_content() {
        return comments_content;
    }

    public void setComments_content(String comments_content) {
        this.comments_content = comments_content;
    }

    public String getComments_date() {
        return comments_date;
    }

    public void setComments_date(String comments_date) {
        this.comments_date = comments_date;
    }

    public CommentInfo(String user_Head, String user_name, String comments_content, String comments_date) {

        this.user_Head = user_Head;
        this.user_name = user_name;
        this.comments_content = comments_content;
        this.comments_date = comments_date;
    }
}
