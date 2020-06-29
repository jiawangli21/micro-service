package com.equip.service;

import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.po.LoginPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface LoginService {

    @RequestMapping(value = "/login/login",method = RequestMethod.POST)
    public Result login(LoginPO loginPO);

    @RequestMapping(value = "/login/outLogin",method = RequestMethod.GET)
    public Result outLogin();

    @RequestMapping(value = "/login/getVerify",method = RequestMethod.GET)
    public byte[] getVerify() throws IOException;
}
