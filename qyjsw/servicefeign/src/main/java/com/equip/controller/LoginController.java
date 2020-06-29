package com.equip.controller;

import com.equip.entity.Result;
import com.equip.po.LoginPO;
import com.equip.service.LoginService;
import com.equip.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginPO loginPO) {
        return loginService.login(loginPO);
    }

    @GetMapping("/outLogin")
    public Result outLogin(){
        return loginService.outLogin();
    }

    @GetMapping(value = "/getVerify")
    public void getVerify() throws IOException {
        byte[] bytes = loginService.getVerify();
        ResponseUtils.buildImageRes(bytes);
    }

}
