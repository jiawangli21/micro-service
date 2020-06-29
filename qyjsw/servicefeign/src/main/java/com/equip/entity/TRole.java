package com.equip.entity;

import java.io.Serializable;
import java.sql.Date;

public class TRole implements Serializable {

    private Long roleId;

    private String roleName;

    private String roleCreater;

    private Date createTime;

    public String getRoleCreater() {
        return roleCreater;
    }

    public void setRoleCreater(String roleCreater) {
        this.roleCreater = roleCreater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Override
    public String toString() {
        return "TRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
