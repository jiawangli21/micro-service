package com.java.vo;

import com.java.entity.TMenu;
import com.java.entity.TRight;

import java.io.Serializable;
import java.util.List;

public class RightMenuVO  implements Serializable {

    private List<TRight> tRightList;

    private List<TMenu> tMenuList;


    public List<TRight> gettRightList() {
        return tRightList;
    }

    public void settRightList(List<TRight> tRightList) {
        this.tRightList = tRightList;
    }

    public List<TMenu> gettMenuList() {
        return tMenuList;
    }

    public void settMenuList(List<TMenu> tMenuList) {
        this.tMenuList = tMenuList;
    }
}