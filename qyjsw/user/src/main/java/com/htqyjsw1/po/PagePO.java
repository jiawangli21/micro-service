package com.htqyjsw1.po;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class PagePO implements Serializable {

    @ApiParam("当前页数")
    private int page;

    @ApiParam("每页显示的数据条数")
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PagePO{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
