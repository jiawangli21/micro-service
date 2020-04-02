package com.htqyjsw1.po;

import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.entity.TRoleRightRel;

import java.util.List;

public class RolePO {

    private TRole tRole;

    private List<TRoleRightRel> tRoleRightRelList;

    public TRole gettRole() {
        return tRole;
    }

    public void settRole(TRole tRole) {
        this.tRole = tRole;
    }

    public List<TRoleRightRel> gettRoleRightRelList() {
        return tRoleRightRelList;
    }

    public void settRoleRightRelList(List<TRoleRightRel> tRoleRightRelList) {
        this.tRoleRightRelList = tRoleRightRelList;
    }
}
