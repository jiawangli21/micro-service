package com.htqyjsw1.service;


import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TMenu;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MenuService {

    /**
     * @desc 添加菜单
     * @param tMenu
     * @return
     */
    public Result addMenu(TMenu tMenu);

    /**
     * @desc 删除菜单信息
     * @param menuId
     * @return
     */
    public Result deleteMenu(Long menuId);

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


}
