package com.equip.po;

import com.equip.entity.TRoleRightRel;

import java.util.Date;
import java.util.List;

public class RolePO {

    private Long roleId;

    private String roleName;


    private String roleCreater;

    private Date createTime;

    private List<TRoleRightRel> tRoleRightRelList;

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
