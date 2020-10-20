package com.education.subject.mapper;

import com.education.subject.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface UserMapper extends BaseMapper<User> {
    boolean updatePwd(String username,String password);

}
