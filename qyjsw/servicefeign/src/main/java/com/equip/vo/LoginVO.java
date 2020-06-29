package com.equip.vo;


import com.equip.entity.TUser;

import java.io.Serializable;

/**
 * @desc 登录成功返回
 */
public class LoginVO implements Serializable {

    private TUser tUser;

    private String token;

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
