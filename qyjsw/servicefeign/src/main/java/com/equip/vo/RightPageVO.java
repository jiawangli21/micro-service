package com.equip.vo;

import com.equip.entity.TPage;
import com.equip.entity.TRight;


import java.io.Serializable;
import java.util.List;

public class RightPageVO  implements Serializable {

    private List<TRight> tRightList;

    private List<TPage> tPageList;


    public List<TRight> gettRightList() {
        return tRightList;
    }

    public void settRightList(List<TRight> tRightList) {
        this.tRightList = tRightList;
    }

    public List<TPage> gettPageList() {
        return tPageList;
    }

    public void settPageList(List<TPage> tPageList) {
        this.tPageList = tPageList;
    }
}