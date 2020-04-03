package com.htqyjsw1.vo;

import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.entity.TRight;

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