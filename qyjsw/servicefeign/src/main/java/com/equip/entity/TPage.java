package com.equip.entity;

import java.io.Serializable;

public class TPage  implements Serializable {

    private Long pageId;

    private String pageUrl;

    private String pageDesc;

    private String rightName;

    private String rightId;

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }


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

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof TPage){
            TPage tPage = (TPage)obj;
            if(tPage.pageDesc.equals(this.pageDesc)
                    && tPage.rightName.equals(this.rightName)
                    && tPage.pageId.equals(this.pageId)
                    && tPage.pageUrl.equals(this.pageUrl)
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}