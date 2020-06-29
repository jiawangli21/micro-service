package com.java.service;


import com.java.entity.Result;
import com.java.entity.TMenu;
import com.java.po.MenuPO;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MenuService {

    /**
     * @desc 添加菜单
     * @param menuPO
     * @return
     */
    public Result addMenu(MenuPO menuPO);

    /**
     * @desc 删除菜单信息
     * @param menuId
     * @return
     */
    public Result deleteMenu(Long menuId, HttpServletRequest request);

    /**
     * @desc 更新菜单信息
     * @param tMenu
     * @return
     */
    public Result updateMenu(TMenu tMenu, HttpServletRequest request);

    /**
     * @desc 查询所有菜单信息
     * @return List<TPage>
     */
    public List<TMenu> findAll();

    /**
     * @desc 批量查询
     * @param menuIdList
     * @return
     */
    List<TMenu> selectByIds(List<Long> menuIdList);

    /**
     * @desc 查询用户关联的菜单信息，返回菜单集合
     * @param userId
     * @return
     */
    public Result findUserMenu(@ApiParam("用户编号") Long userId);

    /**
     * @desc 分页查询菜单信息
     * @param page
     * @param pageSize
     * @return
     */
    public Result findByPage(int page, int pageSize);


    public int count();

}
