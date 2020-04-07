package com.htqyjsw1.config;

import com.htqyjsw1.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {

    @Autowired
    private static RedisUtils redisUtils ;


    public static void main(String[] args) {
        redisUtils.set("userName","jiawangli");
    }
}
