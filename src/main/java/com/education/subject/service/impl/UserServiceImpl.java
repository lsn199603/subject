package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.User;
import com.education.subject.mapper.UserMapper;
import com.education.subject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean updatePwd(String username,String password){
        return userMapper.updatePwd(username,password);
    }
}
