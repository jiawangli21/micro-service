package com.java.entity;
import lombok.Data;

import java.util.List;

@Data
public class MenuVV {
    private int code;
    private String str;
    private int count;
    private List<Menu> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(List<Menu> data) {
        this.data = data;
    }
}
