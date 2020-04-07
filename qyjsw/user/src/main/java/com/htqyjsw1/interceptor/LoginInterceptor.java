package com.htqyjsw1.interceptor;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.utils.JwtUtil;
import com.htqyjsw1.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private String LOGINURL = "http://192.168.100.17:8090/login";

    @Autowired
    private RedisUtils redisUtils ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getServletPath();

        String userName = request.getParameter("userName");

        String token = request.getHeader("Authorization");
        Long expire = redisUtils.getExpire(userName);
        if ("/login/login".equals(path)){
            logger.info("-----------------返回页面到登录页------------------");
            response.sendRedirect(LOGINURL);
        }
        if (null == token){
            logger.info("-----------------当前用户未登录》》》返回页面到登录页------------------");
            response.sendRedirect(LOGINURL);
            return false;
        }else{
            //获取redis中的token
            String redisToken = (String) redisUtils.get(userName);
            if(redisToken == null ){
                logger.error("【当前用户token在redis中不存在】,userName:",userName);
                response.sendRedirect(LOGINURL);
                return false;
            }
            if(expire <= 0){
                logger.info("【当前用户token已失效】,currentUser:{}",userName);
                response.sendRedirect(LOGINURL);
                return false;
            }
            //为当前登录用户重置登录活性
            redisUtils.set(userName,token,60*60*24, TimeUnit.SECONDS);
        }

        return true;

    }

}
