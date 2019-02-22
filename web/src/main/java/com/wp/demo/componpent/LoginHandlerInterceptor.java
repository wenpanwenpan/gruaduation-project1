package com.wp.demo.componpent;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/12/20.
 * 自定义的登录拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从session请求域中获取该用户是否已经登录
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //该用户未登录
            request.setAttribute("msg","没有权限，请先登录！");
            //进行服务器端跳转
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //该用户已经登录,则拦截器放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
