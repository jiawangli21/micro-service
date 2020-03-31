package com.htqyjsw1.entity;

import java.math.BigInteger;

public class Menu {

    private BigInteger menu_id;
    private String menu_name;

    private BigInteger menu_parent;

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_parent=" + menu_parent +
                '}';
    }

    public BigInteger getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(BigInteger menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public BigInteger getMenu_parent() {
        return menu_parent;
    }

    public void setMenu_parent(BigInteger menu_parent) {
        this.menu_parent = menu_parent;
    }
}
