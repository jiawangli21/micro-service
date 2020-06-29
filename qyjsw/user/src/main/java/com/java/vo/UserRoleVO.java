package com.java.vo;

import com.java.entity.TFunction;
import com.java.entity.TMenu;
import com.java.entity.TPage;
import com.java.entity.TRole;

import java.io.Serializable;
import java.util.List;

public class UserRoleVO implements Serializable {

    private Long roleId;

    private String roleName;

    private List<TRole> tRoles;

    private List<TMenu> tMenus;

    private List<TFunction> tFunctions;

    private List<TPage> tPages;

    public List<TRole> gettRoles() {
        return tRoles;
    }

    public void settRoles(List<TRole> tRoles) {
        this.tRoles = tRoles;
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

    public List<TMenu> gettMenus() {
        return tMenus;
    }

    public void settMenus(List<TMenu> tMenus) {
        this.tMenus = tMenus;
    }

    public List<TFunction> gettFunctions() {
        return tFunctions;
    }

    public void settFunctions(List<TFunction> tFunctions) {
        this.tFunctions = tFunctions;
    }

    public List<TPage> gettPages() {
        return tPages;
    }

    public void settPages(List<TPage> tPages) {
        this.tPages = tPages;
    }

    @Override
    public String toString() {
        return "UserRoleVO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", tRoles=" + tRoles +
                ", tMenus=" + tMenus +
                ", tFunctions=" + tFunctions +
                ", tPages=" + tPages +
                '}';
    }
}
