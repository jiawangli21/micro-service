package com.java.vo;

import com.java.entity.TFunction;
import com.java.entity.TMenu;
import com.java.entity.TPage;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class RoleVO  implements Serializable {

    private Long roleId;

    private String roleName;

    private String roleCreater;

    private Date createTime;

    private List<TFunction> tFunctionList;

    private List<TMenu> tMenuList;

    private List<TPage> tPageList;

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
