package com.education.subject.Tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @program: subject
 * @author: lsn
 * @create: 2019-10-23 17:41
 * @description: 关时时间的处理
 */
public class Time {
    //获取当前年份
    public String getNowYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年");
        java.util.Date date = new java.util.Date();
        String endTime = sdf.format(date);
        return endTime;
    }
    //根据当前年份和index值求年份
    public String getDateOfIndex(Integer index){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年");
        java.util.Date date = new java.util.Date();
        String startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,-index);
        java.util.Date dt1 = calendar.getTime();
        startTime = sdf.format(dt1);
        return startTime;
    }
}
