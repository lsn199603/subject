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
    * 高校信息表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("schoolinformation")
    public class Schoolinformation implements Serializable {

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
            * 高校负责人
            */
            @JsonProperty("siPrincipal")
    private String siPrincipal;

            /**
            * 高校负责人电话
            */
            @JsonProperty("siPPhone")
    private String siPPhone;

            /**
            * 高校联系人
            */
            @JsonProperty("siContact")
    private String siContact;

            /**
            * 高校联系人电话
            */
            @JsonProperty("siCPhone")
    private String siCPhone;

            /**
            * 备注
            */
            @JsonProperty("siRemark")
    private String siRemark;

    /**
     * 伪删除
     */
    private Integer sign;

    @TableField(exist = false)
    private List<Schoolinformation> schoolinformationList;
}
