package com.education.subject.entity;

import lombok.Data;

/**
 * @program: subject
 * @author: lsn
 * @create: 2019-10-02 20:32
 * @description: 博士点信息统计sql返回实体类
 */
@Data
public class MasterCount {
    private String schoolname;
    private Integer first; //一级硕士点
    private Integer second; //二级硕士点
    private Integer total;
}
