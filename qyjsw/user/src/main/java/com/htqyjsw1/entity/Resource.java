package com.htqyjsw1.entity;

import java.math.BigInteger;

public class Resource {

    private BigInteger res_id;

    private String res_name;

    private String res_url;

    @Override
    public String toString() {
        return "Resource{" +
                "res_id=" + res_id +
                ", res_name='" + res_name + '\'' +
                ", res_url='" + res_url + '\'' +
                '}';
    }

    public BigInteger getRes_id() {
        return res_id;
    }

    public void setRes_id(BigInteger res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_url() {
        return res_url;
    }

    public void setRes_url(String res_url) {
        this.res_url = res_url;
    }
}
