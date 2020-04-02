package com.htqyjsw1.entity;

public class TFunction {

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
        this.funSubsystemName = funSubsystemName == null ? null : funSubsystemName.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    @Override
    public String toString() {
        return "TFunction{" +
                "funId=" + funId +
                ", funSubsystemName='" + funSubsystemName + '\'' +
                ", funName='" + funName + '\'' +
                '}';
    }
}