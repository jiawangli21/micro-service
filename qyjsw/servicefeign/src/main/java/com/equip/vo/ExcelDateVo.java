package com.equip.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExcelDateVo {

    private List<String> title;
    private List<?> list;

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
