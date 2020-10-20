package com.education.subject.entity;

/**
 * @program: subject
 * @author: lsn
 * @create: 2019-10-10 16:57
 * @description:
 */

public class PrizeCountToArray {
    private String schoolname;
    private String[] Date;
    private String[] Special;
    private String[] First;
    private String[] Second;
    private Integer total;

    private String date1;
    private String date2;
    private String date3;

    private Integer special1;
    private Integer special2;
    private Integer special3;


    private Integer first1;
    private Integer first2;
    private Integer first3;


    private Integer second1;
    private Integer second2;
    private Integer second3;

    public String getDate1() {

            return  Date[0];

    }

    public String getDate2() {
        return  Date[1];
    }

    public String getDate3() {
        return  Date[2];
    }


    public Integer getSpecial1() {
        return Integer.parseInt(Special[0]);
    }

    public Integer getSpecial2() {
        return Integer.parseInt(Special[1]);
    }

    public Integer getSpecial3() {
        return Integer.parseInt(Special[2]);
    }


    public Integer getFirst1() {
        return Integer.parseInt(First[0]);
    }
    public Integer getFirst2() {
        return Integer.parseInt(First[1]);
    }
    public Integer getFirst3() {
        return Integer.parseInt(First[2]);
    }



    public Integer getSecond1() {
        return Integer.parseInt(Second[0]);
    }
    public Integer getSecond2() {
        return Integer.parseInt(Second[1]);
    }
    public Integer getSecond3() {
        return Integer.parseInt(Second[2]);
    }

    public String getSchoolname() {
        return this.schoolname;
    }

    public String[] getDate() {
        return this.Date;
    }

    public String[] getSpecial() {
        return this.Special;
    }

    public String[] getFirst() {
        return this.First;
    }

    public String[] getSecond() {
        return this.Second;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setSchoolname(final String schoolname) {
        this.schoolname = schoolname;
    }

    public void setDate(final String[] Date) {
        this.Date = Date;
    }

    public void setSpecial(final String[] Special) {
        this.Special = Special;
    }

    public void setFirst(final String[] First) {
        this.First = First;
    }

    public void setSecond(final String[] Second) {
        this.Second = Second;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }
}
