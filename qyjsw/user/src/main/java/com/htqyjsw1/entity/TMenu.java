package com.htqyjsw1.entity;

import java.io.Serializable;
import java.util.List;

public class TMenu  implements Serializable {

    private Long menuId;

    private String menuName;

    private Long menuParent;

    private String rightName;

    private Integer menuLevel;

    private String menuUrl;

    private List<TMenu> childrenMenus;


    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<TMenu> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<TMenu> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Long getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(Long menuParent) {
        this.menuParent = menuParent;
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
        if(obj instanceof TMenu){
            TMenu tMenu = (TMenu)obj;
            if(tMenu.menuName.equals(this.menuName)
                    && tMenu.rightName.equals(this.rightName)
                    && tMenu.menuId.equals(this.menuId)
                    && tMenu.menuLevel.equals(this.menuLevel)
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "TMenu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuParent=" + menuParent +
                ", rightName='" + rightName + '\'' +
                ", menuLevel=" + menuLevel +
                ", menuUrl='" + menuUrl + '\'' +
                ", childrenMenus=" + childrenMenus +
                '}';
    }
}