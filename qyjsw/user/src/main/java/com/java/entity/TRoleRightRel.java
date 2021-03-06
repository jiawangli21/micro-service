package com.java.entity;

import java.io.Serializable;

public class TRoleRightRel   implements Serializable {

    private Long rrId;

    private Long roleId;

    private Long eleId;

    private Integer eleType;

    private Long rightId;

    @Override
    public String toString() {
        return "TRoleRightRel{" +
                "rrId=" + rrId +
                ", roleId=" + roleId +
                ", eleId=" + eleId +
                ", eleType=" + eleType +
                ", rightId=" + rightId +
                '}';
    }

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEleId() {
        return eleId;
    }

    public void setEleId(Long eleId) {
        this.eleId = eleId;
    }

    public Integer getEleType() {
        return eleType;
    }

    public void setEleType(Integer eleType) {
        this.eleType = eleType;
    }

    public Long getRightId() {
        return rightId;
    }

    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }
}