package com.htqyjsw1.service;

import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.vo.RoleRelVO;
import com.htqyjsw1.vo.RoleVO;
import com.htqyjsw1.vo.UserRoleVO;

import java.util.List;

public interface RoleService {


    /**
     * @desc 添加角色信息
     * @param role
     * @return
     */
    public String addRole(RolePO role);

    /**
     * @ 删除角色信息
     * @param roleId
     * @return
     */
    public void deleteRole(Long roleId);


    /**
     * @desc 查询角色关联的权限信息，按权限类别进行封装，当添加角色信息时
     * @return
     */
    public RoleRelVO queryRoleRel();


    /**
     * @desc 根据角色id查询角色详情信息，返回角色所拥有的菜单（menu），页面（page），功能（function）权限集合
     * @param roleId
     * @return
     */
    public RoleVO queryDetail(Long roleId);

    /**
     * @desc 根据用户id查询用户所拥有的角色信息，返回角色集合list
     * @param userId
     * @return
     */
    public List<TRole> queryRoleByUserId(Long userId);

    /**
     * @desc 查询所有角色信息
     * @return
     */
    public List<TRole> findAll();

    /**
     * @desc 更新角色
     * @param rolePO
     */
    public String updateRole(RolePO rolePO);

    /**
     * @desc 查询角色拥有的权限信息，返回(type=1 -> 角色-功能，type=2 -> 角色-菜单，type=3 -> 角色-页面，) 关联信息集合
     * @param roleId
     * @param type
     * @return
     */
    public UserRoleVO queryRole(Long roleId, int type ) throws Exception;

}
