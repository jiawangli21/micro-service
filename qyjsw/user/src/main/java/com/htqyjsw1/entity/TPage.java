package com.htqyjsw1.entity;

import java.io.Serializable;

public class TPage  implements Serializable {

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
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc == null ? null : pageDesc.trim();
    }
}