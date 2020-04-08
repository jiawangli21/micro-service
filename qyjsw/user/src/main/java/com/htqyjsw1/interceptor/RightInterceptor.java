package com.htqyjsw1.interceptor;

import com.htqyjsw1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RightInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();

        System.out.println("path = " + path);
        String token = request.getHeader("Authorization");

        Claims claims = TokenUtil.parseJWT(token);
        String tokenKey = claims.get("jti").toString();


        return true;
    }
}
