package com.htqyjsw1.entity;

import java.io.Serializable;

public class TFunction  implements Serializable {

    private Long funId;

    private String funSubsystemName;

    private String funName;

    private String rightName;

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

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

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof TFunction){
            TFunction tFunction = (TFunction)obj;
            if(tFunction.funName.equals(this.funName)
                    && tFunction.rightName.equals(this.rightName)
                    && tFunction.funId.equals(this.funId)
                    && tFunction.funSubsystemName.equals(this.funSubsystemName)
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}