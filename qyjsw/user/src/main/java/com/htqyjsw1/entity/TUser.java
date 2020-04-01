package com.htqyjsw1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class TUser implements Serializable {

    private Integer userId;

    private String userAcc;

    private String userPwd;

    private String userName;

    private String userNickname;

    private Integer userAge;

    private String userGender;

    private String userTel;

    private String userEmail;

    private String userAdd;

    private String userPosition;

    private String userNote;

}
