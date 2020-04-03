package com.htqyjsw1.entity;


import java.io.Serializable;

public class TDeptUserRel  implements Serializable {

    private Long duId;

    private Long deptId;

    private Long userId;

    public Long getDuId() {
        return duId;
    }

    public void setDuId(Long duId) {
        this.duId = duId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
