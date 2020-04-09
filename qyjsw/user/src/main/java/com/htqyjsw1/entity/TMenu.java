package com.htqyjsw1.entity;

import java.io.Serializable;

public class TMenu  implements Serializable {

    private Long menuId;

    private String menuName;

    private Long menuParent;

    private String rightName;

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
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}