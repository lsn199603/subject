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
    * 教学成果信息表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("teachingresult")
    public class Teachingresult implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 完成单位编号
            */
    private String siId;

            /**
            * 完成单位名称
            */
    private String siName;

            /**
            * 成果名称
            */
    private String trName;

            /**
            * 成果级别
            */
    private String trGrade;

            /**
            * 获奖人
            */
    private String trPerson;

            /**
            * 获奖时间
            */
    private String trDate;

            /**
            * 备注
            */
    private String trRemark;

    /**
     * 伪删除
     */
    private Integer sign;

    @TableField(exist = false)
    private List<Teachingresult> teachingresultList;

}
