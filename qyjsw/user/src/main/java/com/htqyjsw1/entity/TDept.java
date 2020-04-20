package com.htqyjsw1.entity;

import java.io.Serializable;

public class TDept  implements Serializable {

    private Long deptId;

    private String deptName;

    private String deptIntroduce;

    private String deptAdd;

    private String deptHead;

    private String  deptTel;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
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

    @Override
    public String toString() {
        return "TDept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptIntroduce='" + deptIntroduce + '\'' +
                ", deptAdd='" + deptAdd + '\'' +
                ", deptHead='" + deptHead + '\'' +
                ", deptTel='" + deptTel + '\'' +
                '}';
    }
}
