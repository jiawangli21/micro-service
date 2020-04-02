package com.htqyjsw1.vo;

import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.entity.TRight;

import java.util.List;

public class RightMenuVO {

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