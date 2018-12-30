package com.triplebro.aran.hustlestore.properties;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/26.
 */


public class Properties {

/**
    http://120.25.96.141:8080/login/request    获取验证码
    phoneNumber
    json：{"message":0}

    http://120.25.96.141:8080/login/registernumber   提交验证码
    phoneNumber
    registerNumber
    json：{"message":1}

    http://120.25.96.141:8080/login/login   登陆
    userPhone
    password
    json：{"message":1}

    http://120.25.96.141:8080/user/update_info
    userPhoneNumber
    userNickName
    userPassword
    json：{"message":1}

    ps: message=1  成功   =0 失败
    所有全是post
*/

public static final String loginAddress = "http://120.25.96.141:8080/login/login";
public static final String requestCodeAddress = "http://120.25.96.141:8080/login/request";
public static final String registernumberAddress = "http://120.25.96.141:8080/login/registernumber";
public static final String upDateInfoAddress = "http://120.25.96.141:8080/user/update_info";
public static final int REQUEST_CODE_CHOOSE = 101;


public static final int FROM_GALLERY = 1;
public static final int SET_SECIMG1 = 101;
public static final int SET_SECIMG2 = 102;
public static final int SET_SECIMG3 = 103;
public static final int SET_SECIMG4 = 104;

}
