package com.equip.vo;

import lombok.Data;

@Data
public class QueryArticleVo {
    private int dataId;

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
