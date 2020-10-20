package com.education.subject.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 学科平台目录表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("subjectplatformmenu")
    public class Subjectplatformmenu implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
           /* @TableId(value = "id", type = IdType.AUTO)
    *//*private Integer id;*/

            /**
            * 学科目录编号
            */
            @JsonProperty("id")
    private String spmId;

            /**
            * 上级目录编号
            */
            @JsonProperty("parentId")
    private String spmParentId;

            /**
            * 学科目录名称
            */
            @JsonProperty("title")
    private String spmName;
    /*
    * 父级名称
    * */

            /**
            * 备注
            */
    private String spmRemark;

    /**
     * 伪删除
     */
    @JsonProperty("checkArr")
    private Integer sign;

}
