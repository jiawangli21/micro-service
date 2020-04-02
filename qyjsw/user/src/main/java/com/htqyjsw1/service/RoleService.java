package com.htqyjsw1.service;

import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.vo.RoleRelVO;
import com.htqyjsw1.vo.RoleVO;

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
    public void deleteRole(Integer roleId);


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
    public RoleVO queryDetail(Integer roleId);

}
