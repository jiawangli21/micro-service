package com.htqyjsw1.po;

import java.io.Serializable;

public class FunctionPO implements Serializable {

    private Long funId;

    private String funSubsystemName;

    private String funName;

    public Long getFunId() {
        return funId;
    }

    public void setFunId(Long funId) {
        this.funId = funId;
    }

    public String getFunSubsystemName() {
        return funSubsystemName;
    }

    public void setFunSubsystemName(String funSubsystemName) {
        this.funSubsystemName = funSubsystemName;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }
}