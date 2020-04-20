package com.htqyjsw1.interceptor;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import com.htqyjsw1.vo.UserRoleVO;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        //user/findAll
        System.out.println("path = " + path);
        String token = request.getHeader("Authorization");

        Claims claims = TokenUtil.parseJWT(token);
        String tokenKey = claims.get("jti").toString();

        String[] s = tokenKey.split("_");
        long userId = Long.valueOf( s[1]);
        //查询缓存
        String key  = "permissions_"+userId;
        List<String> paths = (List<String>) redisUtils.get(key);

        if(paths == null){
            //查询数据库
            paths = new ArrayList<>();
            Result userRight = userService.findUserRight(userId);
            UserRoleVO rightData = (UserRoleVO) userRight.getData();

            List<TPage> pages = rightData.gettPages();

            for (TPage page : pages) {
                paths.add(page.getPageUrl());
            }
            redisUtils.set(key,paths,60*24, TimeUnit.MINUTES);
        }
        if(!paths.contains(path)){
            logger.info("【用户权限校验】，您没有该权限！");

            return false;
        }
        return true;
    }
}
