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
* @author lsn
* @since 2019-11-03
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("teachingresultnation")
    public class Teachingresultnation implements Serializable {

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
            * 获奖时间
            */
    private String trName;

            /**
            * 获奖等级
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
            * 删除标志
            */
    private Integer sign;

    @TableField(exist = false)
    private List<Teachingresultnation> teachingresultnationList;
}
