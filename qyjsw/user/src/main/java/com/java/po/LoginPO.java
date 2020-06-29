package com.java.po;

import io.swagger.annotations.ApiParam;

public class LoginPO {

    @ApiParam("账号")
    private String userAcc;

    @ApiParam("密码")
   private String  password;

    @ApiParam("验证码")
   private String verificationCode;

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
