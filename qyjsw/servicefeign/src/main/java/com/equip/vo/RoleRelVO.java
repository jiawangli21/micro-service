package com.equip.vo;

import java.io.Serializable;

public class RoleRelVO  implements Serializable {

    private RightFunctionVO rightFunctionVO;

    private RightMenuVO rightMenuVO;

    private RightPageVO rightPageVO;

    public RightFunctionVO getRightFunctionVO() {
        return rightFunctionVO;
    }

    public void setRightFunctionVO(RightFunctionVO rightFunctionVO) {
        this.rightFunctionVO = rightFunctionVO;
    }

    public RightMenuVO getRightMenuVO() {
        return rightMenuVO;
    }

    public void setRightMenuVO(RightMenuVO rightMenuVO) {
        this.rightMenuVO = rightMenuVO;
    }

    public RightPageVO getRightPageVO() {
        return rightPageVO;
    }

    public void setRightPageVO(RightPageVO rightPageVO) {
        this.rightPageVO = rightPageVO;
    }
}
