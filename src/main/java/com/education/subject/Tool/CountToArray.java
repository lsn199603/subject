package com.education.subject.Tool;

import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.PrizeCountToArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: subject
 * @author: lsn
 * @create: 2019-10-10 17:44
 * @description: 对数据库返回的数据进行处理
 */
public class CountToArray {

    public List<PrizeCountToArray> ToArray(List<PrizeCount> dList,String startTime, String endTime) {

        List<PrizeCountToArray> newList = new ArrayList<>();
        List<String> list= findDate(startTime,endTime);
        //list转数组
        String[] newdate = ListToArray(list);
        //数组的长度
        Integer length = list.size();
        /*//判断学校数是否满足搜索条件
        if (dList.size() != schoolList.size()){
            if (schoolList.indexOf(dList) == -1 ){

            }

        }*/
        //年份数是否满足搜索条件
        for (int i = 0;i < dList.size();i++ ){
            PrizeCountToArray prizeCountToArray = new PrizeCountToArray();
            String[] date = dList.get(i).getDate().split(",");
            String[] special = dList.get(i).getSpecial().split(",");
            String[] first =  dList.get(i).getFirst().split(",");
            String[] second = dList.get(i).getSecond().split(",");
            if (date.length != list.size()){
                //special
                    String[] newSpecial = ArrayDisplacement(special,list.indexOf(date[0]),length);
                    String[] newFirst = ArrayDisplacement(first,list.indexOf(date[0]),length);
                    String[] newSecond = ArrayDisplacement(second,list.indexOf(date[0]),length);
                    for (int j = 0;j < list.indexOf(date[0]);j++){
                        newSpecial[j] = "0";
                        newFirst[j] = "0";
                        newSecond[j] = "0";
                    }
                    for (int k = list.indexOf(date[date.length-1])+1;k < list.size();k++){
                        newSpecial[k] = "0";
                        newFirst[k] = "0";
                        newSecond[k] = "0";
                    }
                    prizeCountToArray.setDate(newdate);
                    prizeCountToArray.setSpecial(newSpecial);
                    prizeCountToArray.setFirst(newFirst);
                    prizeCountToArray.setSecond(newSecond);


            }else {
                prizeCountToArray.setDate(date);
                prizeCountToArray.setSpecial(special);
                prizeCountToArray.setFirst(first);
                prizeCountToArray.setSecond(second);
            }
            prizeCountToArray.setSchoolname(dList.get(i).getSchoolname());
            prizeCountToArray.setTotal(dList.get(i).getTotal());
            newList.add(i,prizeCountToArray);
        }
        return newList;
    }
    /*
    * List转数组
    * */
    public String[] ListToArray(List<String> list){
        String[] date = new String[list.size()];
        list.toArray(date);
        return date;
    }
    /*
    * 根据开始时间和结束时间获取中间的时间
    * */
    public  List<String> findDate(String startTime,String endTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年");
        try {
            Date dE = simpleDateFormat.parse(endTime);
            Date dB = simpleDateFormat.parse(startTime);
            List<String> date = new ArrayList<>();
            date.add(startTime);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(dB);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(dE);
            while (dE.after(calBegin.getTime())){
                calBegin.add(Calendar.YEAR,1);
                date.add(simpleDateFormat.format(calBegin.getTime()));
            }
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 数组右移k位
    * */
    public String[] ArrayDisplacement(String[] arr,int k,int length){
        //定义一个和原数组长度相同的新数组
        String[] newarr = new String[length];
        //数组内元素全部右移k位
        for (int i = 0;i < arr.length;i++){
            newarr[i+k] = arr[i];
        }
        return newarr;
    }
}
