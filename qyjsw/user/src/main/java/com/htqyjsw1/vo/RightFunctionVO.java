package com.htqyjsw1.vo;

import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.entity.TRight;

import java.util.List;

public class RightFunctionVO {

    private List<TRight> tRightList;

    private List<TFunction> tFunctionList;


    public List<TRight> gettRightList() {
        return tRightList;
    }

    public void settRightList(List<TRight> tRightList) {
        this.tRightList = tRightList;
    }

    public List<TFunction> gettFunctionList() {
        return tFunctionList;
    }

    public void settFunctionList(List<TFunction> tFunctionList) {
        this.tFunctionList = tFunctionList;
    }
}