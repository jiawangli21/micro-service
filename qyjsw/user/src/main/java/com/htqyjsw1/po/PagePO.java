package com.htqyjsw1.po;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class PagePO implements Serializable {

    private Long pageId;

    private String pageUrl;

    private String pageDesc;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }
}
