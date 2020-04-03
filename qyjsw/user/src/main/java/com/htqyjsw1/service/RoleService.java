package com.htqyjsw1.service;

import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.vo.RoleRelVO;
import com.htqyjsw1.vo.RoleVO;

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
     * @desc 查询角色关联的权限信息，当添加角色信息时
     * @return
     */
    public RoleRelVO queryRoleRel();


    /**
     * @desc 根据角色id查询角色详情信息
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

}
