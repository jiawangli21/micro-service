package com.java.interceptor;

import com.java.service.UserService;
import com.java.utils.RedisUtils;
import com.java.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RightInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(RightInterceptor.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();

        if (path.contains("findByPage")){
            path = "/"+ path.split("/")[1]+"/"+ path.split("/")[2];
        }
        //user/findAll
        System.out.println("path = " + path);
        String token = request.getHeader("Authorization");

        Claims claims = TokenUtil.parseJWT(token);
        String tokenKey = claims.get("jti").toString();


        String[] s = tokenKey.split("_");
        long userId = Long.valueOf( s[1]);
        //查询缓存
        String key  = "permissions_"+userId;
        List<String> paths = null;
        if (redisUtils.get(key)!=null) {
            paths = (List<String>) redisUtils.get(key);
        }
        if(paths == null){
            //查询数据库

            paths  = userService.queryAllowPaths(userId);

            redisUtils.set(key,paths,60*24, TimeUnit.MINUTES);
        }
        if(!paths.contains(path)){
            logger.info("【用户权限校验】，您没有该权限！");
//            throw new RightException("您没有该权限！");
//            response.sendRedirect();
            return false;
        }
        return true;
    }
}
