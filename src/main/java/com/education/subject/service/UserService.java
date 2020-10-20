package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface UserService extends IService<User> {
    boolean updatePwd(String username,String password);

}
