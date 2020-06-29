package com.java.vo;

import com.java.entity.TFunction;
import com.java.entity.TRight;

import java.io.Serializable;
import java.util.List;

public class RightFunctionVO  implements Serializable {

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