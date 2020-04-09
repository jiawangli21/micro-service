package com.htqyjsw1.interceptor;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${login.port}")
    private String port;

    @Value("${login.host}")
    private  String host;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login_path = "http://"+ host+":"+ port;
        String path = request.getServletPath();
        System.out.println("path = "+path);

        String token = request.getHeader("Authorization");
        if (null == token){
            logger.info("-----------------当前用户未登录》》》返回页面到登录页------------------");
            response.sendRedirect(login_path);
            return false;
        }

        Claims claims = TokenUtil.parseJWT(token);
        String tokenKey = claims.get("jti").toString();

        //获取redis中的token
        String redisToken = (String) redisUtils.get(tokenKey);
        if(redisToken == null ){
            logger.error("【当前用户token在redis中不存在】,userName:",tokenKey);
            response.sendRedirect(login_path);
            return false;
        }
        Long expire = redisUtils.getExpire(tokenKey);
        if(expire <= 0){
            logger.info("【当前用户token已失效】,currentUser:{}",tokenKey);
            response.sendRedirect(login_path);
            return false;
        }
        //校验当前用户请求的token是否和redis中的token相同
        if(token.equals(redisToken)) {
            //为当前登录用户重置登录活性
            redisUtils.set(tokenKey, token, 60*60*24, TimeUnit.SECONDS);
            return true;
        }else{
            logger.info("【当前用户token与redis中的token不符合，请重新登录】,currentUser:{}",tokenKey);
            response.sendRedirect(login_path);
            return false;
        }

    }

}
