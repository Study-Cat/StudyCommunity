package com.study.boot.interception;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginInterceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI();
//        if (url.toLowerCase().indexOf("index")>= 0 ||url.toLowerCase().indexOf("callback")>= 0) {
//            return true;
//        }
//        HttpSession session = request.getSession();
//        if(session.getAttribute("user")!=null){
//            return true;
//        }
//        response.sendRedirect(request.getContextPath()+"/index");
//        return false;
//    }

}
