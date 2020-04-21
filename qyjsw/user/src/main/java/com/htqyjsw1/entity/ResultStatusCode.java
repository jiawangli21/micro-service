package com.htqyjsw1.entity;

public enum  ResultStatusCode {

    OK(200, "OK"),
    HTTP_ERROR_100(100, "1XX错误"),
    HTTP_ERROR_300(300, "退出登录异常，"),
    HTTP_ERROR_400(400, "请求异常!"),
    HTTP_ERROR_500(500, "错误"),
    TIME_OUT(130, "访问超时"),
    LOGIN_OUT(300, "登录失效，请重新登录！"),
    NOT_LOGIN(301,"登录异常，请重新登录"),
    REQUEST_NOT_FOUND(404, "访问地址不存在！"),

    NOT_EXIST_USER(10000, "该用户不存在"),
    ERROR_PWD(10002,"密码错误"),
    NOT_PARAM_USER_OR_ERROR_PWD(10006, "账号或密码为空"),

    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),

    INVALID_CAPTCHA(30005, "无效的验证码"),
    INVALID_ERROR(30006, "验证码错误"),
    INVALID_IS_NULL(30007, "验证码为空");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
