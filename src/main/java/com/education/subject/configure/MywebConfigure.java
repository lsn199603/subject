package com.education.subject.configure;


import com.education.subject.inteceptor.LoginIntecpeptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * <p>
 * 拦截器
 * 作用：未登录用户不可修改地址栏地址直接进入页面
 * 若出现未登录用户修改地址栏地址进入页面的情况 请将下面函数内的excludePathPatterns路径中对应的内容删除
 * 原则上excludePathPatterns中只应该有登录和注册对应的页面和请求接口
 * </p>
 *
 */

@SpringBootApplication
public class MywebConfigure implements WebMvcConfigurer {

    @Autowired
    private LoginIntecpeptor loginIntecpeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntecpeptor).addPathPatterns("/**")
                .excludePathPatterns("/login.html","/user/login","/js/**","/css/**","/img/**","/layui/**");

    }
}
