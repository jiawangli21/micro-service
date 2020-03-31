package com.htqyjsw1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@ToString
public class User implements Serializable {

    private BigInteger user_id;
    private String user_acc;
    private String user_pwd;
    private String user_nickname;
    private String user_age;
    private String user_gender;
    private BigInteger user_tel;
    private String user_email;
    private String user_add;
    private String user_position;
    private String user_note;


}
