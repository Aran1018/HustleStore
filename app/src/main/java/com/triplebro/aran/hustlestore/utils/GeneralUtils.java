package com.triplebro.aran.hustlestore.utils;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/3/15.
 */


public class GeneralUtils {



    /**
     * @param InMonth
     * @return str
     * @description input double a date
     */
    public static String GetMonthEN(int InMonth) {
        String strMonth= InMonth+"";
        String beginStr=strMonth.substring(0,1);
        if ("0".equals(beginStr)){
            InMonth = Integer.parseInt(strMonth.substring(1,2));
        }if (beginStr.isEmpty()){
            return "";
        }

        String strReturn = "";
        String strParaMonthn = "JANUARY_FEBRUARY_MARCH_APRIL_MAY_JUNE_JULY_AUGUST_SEPTEMBER_OCTOBER_NOVEMBER_DECEMBER";
        String[] strSubMonth = strParaMonthn.split("_");
        strReturn = strSubMonth[InMonth - 1];
        return strReturn;
    }

    public static String GetStringDate(String inputDate) {
        if (inputDate.length() > 0) {
            //Input:2019年03月03日
            //Output:DECEMBER  2,2018
            String rtnDate = "";
            String arrDate[] = inputDate.split(" ");
            String year = arrDate[0].substring(0, 4);
            int month = Integer.parseInt(arrDate[0].substring(5, 7));
            String ENmonth = GeneralUtils.GetMonthEN(month);
            String day = arrDate[0].substring(8, 10);
            rtnDate ="@ "+ENmonth+"  "+day+","+year;
            return rtnDate;
        } else {

            return "";
        }
    }




    }
