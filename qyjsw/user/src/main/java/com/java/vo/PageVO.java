package com.java.vo;

import com.java.entity.*;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.List;

public class PageVO implements Serializable {

    @ApiParam("当前页数")
    private int page;

    @ApiParam("每页显示的数据条数")
    private int pageSize;

    private int maxPage;

    private List<TUser> userlist;

    private List<TDept> deptList;

    private List<TRole> roleList;

    private List<TRight> rightList;

    private List<TMenu> menuList;

    private  List<TPage> pageList;

    private List<TFunction> functionList;

    public List<TPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<TPage> pageList) {
        this.pageList = pageList;
    }

    public List<TFunction> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<TFunction> functionList) {
        this.functionList = functionList;
    }

    public List<TMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<TMenu> menuList) {
        this.menuList = menuList;
    }

    public List<TRight> getRightList() {
        return rightList;
    }

    public void setRightList(List<TRight> rightList) {
        this.rightList = rightList;
    }

    public List<TRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TRole> roleList) {
        this.roleList = roleList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<TUser> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<TUser> userlist) {
        this.userlist = userlist;
    }

    public List<TDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<TDept> deptList) {
        this.deptList = deptList;
    }

    public int countMaxPage(int count, int pageSize){

        if(count%pageSize == 0){
            maxPage = count/pageSize;
        }else{
            maxPage = count/pageSize + 1;
        }
        return maxPage;
    }

    public int countPage(int p,int maxPage){
        if(p<=0){
            p = 1;
        }else if (maxPage==0){
            p = 1;
        } else if(p > maxPage){
            p  = maxPage;
        }
        return p;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", maxPage=" + maxPage +
                ", userlist=" + userlist +
                ", deptList=" + deptList +
                ", roleList=" + roleList +
                ", rightList=" + rightList +
                '}';
    }
}
