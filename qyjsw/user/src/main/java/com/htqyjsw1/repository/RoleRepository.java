package com.htqyjsw1.repository;


import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.entity.TRoleRightRel;

import java.util.List;

public interface RoleRepository {

    /**
     * 添加角色信息
     * @param tRole
     * @return
     */
    public int insert(TRole tRole);

    /**
     * @desc 添加角色权限信息
     * @param tRoleRightRels
     */
    public void insertRoleRightRel(List<TRoleRightRel> tRoleRightRels);

    /**
     * @ 删除角色信息
     * @param roleId
     * @return
     */
    public void deleteRole(Integer roleId);

    /**
     * @desc 删除角色权限关联信息
     * @param roleId
     */

    public void  deleteRoleRightRel(Integer roleId);
    /**
     * @desc 根据角色id，查询角色权限信息
     * @param roleId
     * @return
     */
    public List<TRoleRightRel> queryRoleRightRel(Integer roleId);

    /**
     * @desc 根据角色id查询,角色详情（包含关联的权限信息：菜单，页面，功能）
     * @param roleId
     * @return
     */
    public TRole queryById(Integer roleId);
}
