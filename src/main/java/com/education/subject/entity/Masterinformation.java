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
    * 硕士点信息表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("masterinformation")
    public class Masterinformation implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 高校编号
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
            @JsonProperty("mClass")
    private String mClass;

            /**
            * 级别
            */
            @JsonProperty("mGrade")
    private String mGrade;

            /**
            * 批准时间
            */
            @JsonProperty("mTime")
    private String mTime;

            /**
            * 备注
            */
            @JsonProperty("mRemark")
    private String mRemark;
    /**
     * 伪删除
     */
    private Integer sign;

    @TableField(exist = false)
    private List<Masterinformation> masterinformationList;


}
