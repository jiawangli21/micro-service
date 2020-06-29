package com.java.vo;

import com.java.entity.TDept;
import com.java.entity.TRole;

import java.io.Serializable;
import java.util.List;

public class UserVO implements Serializable {

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

    private List<TDept> hasDeptList;

    private List<TRole> hasRoleList;

    private List<TDept> tDeptList;

    private List<TRole> tRoleList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public List<TDept> gettDeptList() {
        return tDeptList;
    }

    public void settDeptList(List<TDept> tDeptList) {
        this.tDeptList = tDeptList;
    }

    public List<TRole> gettRoleList() {
        return tRoleList;
    }

    public void settRoleList(List<TRole> tRoleList) {
        this.tRoleList = tRoleList;
    }

    public List<TDept> getHasDeptList() {
        return hasDeptList;
    }

    public void setHasDeptList(List<TDept> hasDeptList) {
        this.hasDeptList = hasDeptList;
    }

    public List<TRole> getHasRoleList() {
        return hasRoleList;
    }

    public void setHasRoleList(List<TRole> hasRoleList) {
        this.hasRoleList = hasRoleList;
    }
}
