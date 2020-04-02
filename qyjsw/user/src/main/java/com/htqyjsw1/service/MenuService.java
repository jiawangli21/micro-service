package com.htqyjsw1.service;


import com.htqyjsw1.entity.TMenu;
import java.util.List;

public interface MenuService {

    /**
     * @desc 添加菜单
     * @param tMenu
     * @return
     */
    public String addMenu(TMenu tMenu);

    /**
     * @desc 删除菜单信息
     * @param menuId
     * @return
     */
    public String deleteMenu(Long menuId);

    /**
     * @desc 更新菜单信息
     * @param tMenu
     * @return
     */
    public void updateMenu(TMenu tMenu);

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
}
