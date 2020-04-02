package com.htqyjsw1.vo;

import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.entity.TPage;

import java.util.List;

public class RoleVO {

    private Long roleId;

    private String roleName;

    private List<TFunction> tFunctionList;

    private List<TMenu> tMenuList;

    private List<TPage> tPageList;

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

    public List<TFunction> gettFunctionList() {
        return tFunctionList;
    }

    public void settFunctionList(List<TFunction> tFunctionList) {
        this.tFunctionList = tFunctionList;
    }

    public List<TMenu> gettMenuList() {
        return tMenuList;
    }

    public void settMenuList(List<TMenu> tMenuList) {
        this.tMenuList = tMenuList;
    }

    public List<TPage> gettPageList() {
        return tPageList;
    }

    public void settPageList(List<TPage> tPageList) {
        this.tPageList = tPageList;
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", tFunctionList=" + tFunctionList +
                ", tMenuList=" + tMenuList +
                ", tPageList=" + tPageList +
                '}';
    }
}
