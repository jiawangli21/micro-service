package micro.service.entity;

import java.math.BigInteger;

public class Page {

    private BigInteger page_id;

    private String page_url;

    private String page_desc;

    public BigInteger getPage_id() {
        return page_id;
    }

    public void setPage_id(BigInteger page_id) {
        this.page_id = page_id;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public String getPage_desc() {
        return page_desc;
    }

    public void setPage_desc(String page_desc) {
        this.page_desc = page_desc;
    }
}
