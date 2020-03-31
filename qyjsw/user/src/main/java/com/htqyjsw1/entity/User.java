package com.htqyjsw1.entity;

import lombok.Value;

import java.util.Date;

@Value
public class User {
    private long user_id;
    private String user_name;
    private String user_pwd;
    private String user_nickname;
    private String user_gender;
    private String user_tel;
    private Date user_regdate;
    private String user_add;

}
