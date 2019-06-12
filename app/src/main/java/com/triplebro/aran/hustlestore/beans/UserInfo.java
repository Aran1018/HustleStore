package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/29.
 */


public class UserInfo {

    int user_id;
    String user_name;
    String user_introduction;
    String user_Head;


    public UserInfo() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_introduction() {
        return user_introduction;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
    }

    public String getUser_Head() {
        return user_Head;
    }

    public void setUser_Head(String user_Head) {
        this.user_Head = user_Head;
    }
}
