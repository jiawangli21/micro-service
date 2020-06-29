package com.equip.service;

import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.po.RolePO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface RoleService {

    /**
     * @desc 添加角色信息
     * @param role
     * @return
     */
    @RequestMapping(value = "/role/addRole",method = RequestMethod.PUT)
    public Result addRole(RolePO role);

    /**
     * @ 删除角色信息
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/role/deleteRole",method = RequestMethod.DELETE)
    public Result deleteRole(@RequestParam("roleId") Long roleId);


    /**
     * @desc 查询所有角色关联的权限信息（包括菜单，功能，页面），按权限类别进行封装，角色授权操作时
     * @return
     */
    @RequestMapping(value = "/role/queryRoleRel",method = RequestMethod.GET)
    public Result queryRoleRel();


    /**
     * @desc 根据角色id查询角色详情信息，返回角色所拥有的菜单（menu），页面（page），功能（function）权限集合
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/role/queryDetail",method = RequestMethod.GET)
    public Result queryDetail(@RequestParam("roleId") Long roleId);

    /**
     * @desc 查询所有角色信息
     * @return
     */
    @RequestMapping(value = "/role/findAll",method = RequestMethod.GET)
    public Result findAll();

    /**
     * @desc 更新角色
     * @param rolePO
     */
    @RequestMapping(value = "/role/updateRole",method = RequestMethod.POST)
    public Result updateRole(RolePO rolePO);

    /**
     * @desc 分页查询角色信息
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/role/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result findByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    @RequestMapping(value = "/role/count",method = RequestMethod.GET)
    public Result count();

    /**
     * @desc 查询某个角色的权限关联信息
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/role/queryRoleRelById",method = RequestMethod.GET)
    public Result queryRoleRelById(@RequestParam("roleId") Long roleId);



}
