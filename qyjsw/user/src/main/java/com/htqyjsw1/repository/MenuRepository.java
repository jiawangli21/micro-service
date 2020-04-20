package com.htqyjsw1.repository;

import com.htqyjsw1.entity.TMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRepository {

    /**
     * @desc 添加菜单
     * @param tMenu
     * @return
     */
    public int insert(TMenu tMenu);

    /**
     * @desc 删除菜单信息
     * @param menuId
     * @return
     */
    public int delete(Long menuId);

    public void deleteByParentId(Long menuId);

    /**
     * @desc 更新菜单信息
     * @param tMenu
     * @return
     */
    public void update(TMenu tMenu);

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
    public List<TMenu> selectByIds(@Param("menuIdList") List<Long> menuIdList);

    /**
     * @desc 查询子菜单菜单信息
     * @param menuId
     * @return
     */
    public List<TMenu> findChildenMenus(Long menuId);

    public int count();

    /**
     * @desc 分页查询菜单信息
     * @param start
     * @param pageSize
     * @return
     */
    public List<TMenu> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);
}
