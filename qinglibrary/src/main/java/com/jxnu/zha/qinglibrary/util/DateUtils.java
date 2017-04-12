package com.jxnu.zha.qinglibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class DateUtils {

    // yyyy-MM-dd hh:mm:ss 12小时制
    // yyyy-MM-dd HH:mm:ss 24小时制
    public static final String TYPE_01 = "yyyy-MM-dd HH:mm:ss";

    public static final String TYPE_02 = "yyyy-MM-dd";
    public static final String TYPE_03 = "HH:mm:ss";
    public static final String TYPE_04 = "yyyy年MM月dd日";

    public static final String TYPE_05 = "yyyy.MM.dd";
    public static final String TYPE_06 = "MM月dd日 HH:mm";
    public static final String TYPE_07 = "yyyy年MM月dd日 HH:mm";
    public static final String TYPE_08 = "yyyy.MM.dd HH:mm";

    private static final String[] WEEK = { "天", "一", "二", "三", "四", "五", "六" };
    public static final String XING_QI = "星期";
    public static final String ZHOU = "周";

    public static String formatDate(long time, String format) {
        Calendar cal = Calendar.getInstance();
        if(time>0)
            cal.setTimeInMillis(time);
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    public static String formatDate(String longStr, String format) {
        try {
            return formatDate(Long.parseLong(longStr), format);
        } catch (Exception e) {
        }
        return "";
    }

    public static long formatStr(String timeStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }
        return 0;
    }

    public static String formatStr_Type05(String timeStr, String pattern){


        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return formatDate(sdf.parse(timeStr).getTime(),TYPE_05);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatStr_Type06(String timeStr, String pattern){


        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return formatDate(sdf.parse(timeStr).getTime(),TYPE_06);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatStr_Type07(String timeStr, String pattern){


        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return formatDate(sdf.parse(timeStr).getTime(),TYPE_07);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getWeek(int num, String format) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
        if (weekNum > 7)
            weekNum = weekNum - 7;
        return format + WEEK[weekNum - 1];
    }

    public static String getZhouWeek() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        return format.format(new Date(System.currentTimeMillis())) + " "
                + getWeek(0, ZHOU);
    }

    public static long getLongTime(String time) {
        try {
            time = time.substring(0, time.indexOf('.'));
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String getTime(long time) {
        return new SimpleDateFormat("HH:mm").format(new Date(time));
    }

    /**
     * 转换日期
     * @param timesamp
     * @return
     */
    public static String getDay(long timesamp) {
        if(timesamp == 0L){
            return "未";
        }
        String result = "未";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天" + getTime(timesamp);
                break;
            case 1:
                result = "昨天"+ getTime(timesamp);
                break;
            case 2:
                result = "前天"+ getTime(timesamp);
                break;

            default:
                result = temp + "天前";
                break;
        }
        return result;
    }

    /**
     * 根据年和月获取这个月的天数列表
     * @param year
     * @param month
     * @return
     */
    public static void setDayDataList(ArrayList<String> dayDataList, int year, int month){

        int result;
        if (2==month) {
            if(year%4==0&&year%100!=0||year%400==0){
                result=29;
            }else{
                result=28;
            }
        }
        else if (month==4||month==6||month==9||month==11) {
            result=30;
        }
        else{
            result=31;
        }
        dayDataList.clear();
        for (int i = 1; i <= result; i ++){

            dayDataList.add(i + "");
        }
    }

    /**
     * 获取当月的天数
     * @return
     */
    public static int getDayNum(){

        int year = getYear();
        int month = getMonth() + 1;
        int result;
        if (2==month) {
            if(year%4==0&&year%100!=0||year%400==0){
                result=29;
            }else{
                result=28;
            }
        }
        else if (month==4||month==6||month==9||month==11) {
            result=30;
        }
        else{
            result=31;
        }
        return result;
    }


    /**
     * 获取当前年份
     * @return
     */
    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取当前时间是在当月的哪一天
     * @return
     */
    public static int getDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间的时
     * @return
     */
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前的分钟
     * @return
     */
    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
}
