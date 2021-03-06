package htqyjsw1.entity;

public enum ResultStatusCode {

    OK(200, "OK"),
    HTTP_ERROR_100(100, "1XX错误"),
    HTTP_ERROR_300(300, "退出登录异常，"),
    HTTP_ERROR_400(400, "请求失败!"),
    HTTP_ERROR_500(500, "错误"),
    TIME_OUT(130, "访问超时"),
    KICK_OUT(300, "您已经在其他地方登录，请重新登录！"),
    INVALID_CLIENTID(402, "无效的密钥"),
    REQUEST_NOT_FOUND(404, "访问地址不存在！"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    REPEAT_REQUEST_NOT_ALLOWED(406, "请求重复提交"),
    SYSTEM_ERR(500, "服务器运行异常"),

    NOT_EXIST_USER(10000, "该用户不存在"),
    ERROR_PWD(10002,"密码错误"),
    NOT_PARAM_USER_OR_ERROR_PWD(10006, "账号或密码为空"),
    LOGINED_IN(10001, "该用户已登录"),
    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),
    REDIS_ERROR(10006, "redis异常"),
    REDIS_CONNECT_ERROR(10007, "redis连接异常"),
    INVALID_CAPTCHA(30005, "无效的验证码"),
    INVALID_ERROR(30006, "验证码错误");


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
