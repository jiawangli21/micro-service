package com.htqyjsw1.entity;

public class TRoleRightRel {

    private Long rrId;

    private Long roleId;

    private Long eleId;

    private Integer eleType;

    private Long rightId;

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