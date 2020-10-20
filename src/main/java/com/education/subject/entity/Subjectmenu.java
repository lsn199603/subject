package com.education.subject.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 学科目录表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("subjectmenu")
    public class Subjectmenu implements Serializable {

    private static final long serialVersionUID = 1L;
/*
            *//**
            * 编号
            *//*
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;*/

            /**
            * 学科代码
            */
            @JsonProperty("id")
    private String smId;

            /**
            * 学科名称
            */
            @JsonProperty("title")
    private String smName;

            /**
            * 上级学科代码
            */
            @JsonProperty("parentId")
    private String smParentId;

            /**
            * 备注
            */
    private String smRemark;

    /**
     * 伪删除
     */
    private Integer sign;

}
