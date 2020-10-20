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
    * 
    * </p>
*
* @author lsn
* @since 2019-11-03
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("scienceresult")
    public class Scienceresult implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 科学技术奖序号
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 高校名称
            */
    private String siName;

            /**
            * 项目名称
            */
    private String stName;

            /**
            * 获奖人
            */
    private String stPerson;

            /**
            * 获奖类别
            */
    private String stClass;

            /**
            * 获奖等级
            */
    private String stGrade;

            /**
            * 获奖时间
            */
    private String stTime;

            /**
            * 推荐单位
            */
    private String stRecommend;

            /**
            * 备注
            */
    private String stRemark;

            /**
            * 删除标志
            */
    private Integer sign;

    @TableField(exist = false)
    private List<Scienceresult> scienceresultList;


}
