package com.education.subject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* <p>
    * 博士点信息表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("doctorinformation")
    public class Doctorinformation implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
            @TableId(type = IdType.AUTO,value = "id")
    private Integer id;

            /**
            * 高校代码
            */
    private String siId;

            /**
            * 高校名称
            */

    private String siName;

            /**
            * 学科代码
            */

    private String smId;

            /**
            * 学科名称
            */

    private String smName;

            /**
            * 类别
            */
            @JsonProperty("dClass")
    private String dClass;

            /**
            * 级别
            */
            @JsonProperty("dGrade")
    private String dGrade;

            /**
            * 批准时间
            */
            @JsonProperty("dTime")
    private String dTime;

            /**
            * 备注
            */
            @JsonProperty("dRemark")
    private String dRemark;
   /* public Doctorinformation(Integer id, String siId, String siName, String smId, String smName,String dClass,String dGrade,String dTime,String dRemark) {
        this.id = id;
        this.siId = siId;
        this.siName = siName;
        this.smId = smId;
        this.smName = smName;
        this.dClass = dClass;
        this.dGrade = dGrade;
        this.dTime = dTime;
        this.dRemark = dRemark;
    }*/
            /**
            * 伪删除
            */
    private Integer sign;

    /*public Doctorinformation(){
        super();
    }*/
    @TableField(exist = false)
    private List<Doctorinformation> doctorinformationList;

}
