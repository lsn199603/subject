package com.education.subject.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 用户表
    * </p>
*
* @author zhangle
* @since 2019-07-20
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 用户编号
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 用户名
            */
    private String username;

            /**
            * 密码
            */
    private String passward;

            /**
            * 类型
            */
    private String type;

            /**
            * 备注
            */
    private String remark;

    /**
     * 伪删除
     */
    private Integer sign;

}
