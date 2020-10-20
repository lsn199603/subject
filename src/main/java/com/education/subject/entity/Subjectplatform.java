package com.education.subject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* <p>
    * 学科平台信息表

    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("subjectplatform")
    public class Subjectplatform implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 学科平台目录编号
            */
    private String spmId;

            /**
            * 学科平台目录名称
            */
    private String spmName;

            /**
            * 学科平台名称
            */
    private String spName;

            /**
            * 高校编号
            */
    private String siId;

            /**
            * 高校名称
            */
    private String siName;

            /**
            * 联系人
            */
    private String spContact;

            /**
            * 联系电话
            */
    private String spCPhone;

            /**
            * 备注
            */
    private String spRemark;

    /**
     * 伪删除
     */
    private Integer sign;

    @TableField(exist = false)
    private List<Subjectplatform> subjectplatformList;
}
