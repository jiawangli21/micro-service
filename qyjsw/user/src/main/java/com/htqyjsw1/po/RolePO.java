package com.htqyjsw1.po;

import com.htqyjsw1.entity.TRoleRightRel;

import java.util.List;

public class RolePO {

    private Long roleId;

    private String roleName;

    private List<TRoleRightRel> tRoleRightRelList;

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
        this.roleName = roleName;
    }

    public List<TRoleRightRel> gettRoleRightRelList() {
        return tRoleRightRelList;
    }

    public void settRoleRightRelList(List<TRoleRightRel> tRoleRightRelList) {
        this.tRoleRightRelList = tRoleRightRelList;
    }

    @Override
    public String toString() {
        return "RolePO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", tRoleRightRelList=" + tRoleRightRelList +
                '}';
    }
}
