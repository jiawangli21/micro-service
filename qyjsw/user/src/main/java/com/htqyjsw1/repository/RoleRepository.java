package com.htqyjsw1.repository;


import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.entity.TRoleRightRel;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleRepository {

    /**
     * 添加角色信息
     * @param rolePO
     * @return
     */
    public int insert(RolePO rolePO);

    /**
     * @desc 添加角色权限信息
     * @param tRoleRightRels
     */
    public void insertRoleRightRel(@Param("list") List<TRoleRightRel> tRoleRightRels);

    /**
     * @ 删除角色信息
     * @param roleId
     * @return
     */
    public void deleteRole(Long roleId);

    /**
     * @desc 删除（角色-权限）关联信息
     * @param roleId
     */

    public void  deleteRoleRightRel(Long roleId);

    /**
     * @desc 删除（用户-角色）信息
     * @param roleId
     */
    public void  deleteUserRoleRel(Long roleId);

    /**
     * @desc 根据角色id，查询角色权限信息
     * @param roleId
     * @return
     */
    public List<TRoleRightRel> queryRoleRightRel(Long roleId);

    /**
     * @desc 根据角色id查询,角色详情（包含关联的权限信息：菜单，页面，功能）
     * @param roleId
     * @return
     */
    public TRole queryById(Long roleId);

    /**
     * @desc 根据用户id查询用户所拥有的角色信息，返回角色集合list
     * @param userId
     * @return
     */
    public List<TRole> queryRoleByUserId(@Param("userId") Long userId);

    /**
     * @desc 查询所有角色信息
     * @return
     */
    public List<TRole> findAll();

    /**
     * @desc 更新角色
     * @param rolePO
     */
    public void update(RolePO rolePO);


    /**
     * @desc 查询角色拥有的权限信息，返回(type=1 -> 角色-功能，type=2 -> 角色-菜单，type=3 -> 角色-页面，) 关联信息集合
     * @param roleId
     * @param type
     * @return
     */
    public UserRoleVO queryRole(@Param("roleId") Long roleId , @Param("type") int type ) throws  Exception ;
}
