package com.htqyjsw1.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.htqyjsw1.entity.TUser;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenUtil {

    public String getToken(TUser user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getUserId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUserPwd()));
        return token;
    }
}
