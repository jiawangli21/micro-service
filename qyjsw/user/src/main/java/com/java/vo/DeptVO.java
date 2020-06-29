package com.java.vo;

import com.java.entity.TUser;

import java.io.Serializable;
import java.util.List;

public class DeptVO implements Serializable {

    private Integer deptId;

    private String deptName;

    private String deptIntroduce;

    private String deptAdd;

    private String deptHead;

    private String  deptTel;

    private List<TUser> userList;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptIntroduce() {
        return deptIntroduce;
    }

    public void setDeptIntroduce(String deptIntroduce) {
        this.deptIntroduce = deptIntroduce;
    }

    public String getDeptAdd() {
        return deptAdd;
    }

    public void setDeptAdd(String deptAdd) {
        this.deptAdd = deptAdd;
    }

    public String getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(String deptHead) {
        this.deptHead = deptHead;
    }

    public String getDeptTel() {
        return deptTel;
    }

    public void setDeptTel(String deptTel) {
        this.deptTel = deptTel;
    }

    public List<TUser> getUserList() {
        return userList;
    }

    public void setUserList(List<TUser> userList) {
        this.userList = userList;
    }
}
