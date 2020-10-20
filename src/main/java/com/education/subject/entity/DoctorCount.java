package com.education.subject.entity;

import lombok.Data;

/**
 * @program: subject
 * @author: lsn
 * @create: 2019-10-02 16:55
 * @description:
 */
@Data
public class DoctorCount {
    private String schoolname;
    private Integer first; //一级博士点
    private Integer second; //二级博士点
    private Integer total;
}
