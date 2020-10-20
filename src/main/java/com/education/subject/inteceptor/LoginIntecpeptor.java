package com.education.subject.inteceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 放行器
 * 根据session中是否对象来放行
 * 对象来自相应的登录功能中
 */

@Component
public class LoginIntecpeptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURL().toString();

        if (session.getAttribute("user") != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
    }
}
