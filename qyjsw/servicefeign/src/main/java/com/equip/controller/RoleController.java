package com.equip.controller;

import com.equip.entity.Result;
import com.equip.po.RolePO;
import com.equip.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 角色信息管理
 * @author lijiawang
 * @dtae 2020/4/27
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping("/addRole")
    public Result addRole(@RequestBody RolePO role){
        Result result = roleService.addRole(role);
        return result;
    }

    @DeleteMapping("/deleteRole")
    public Result deleteRole(Long roleId){
        Result result = roleService.deleteRole(roleId);
        return result;
    }

    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody RolePO role){
        Result result = roleService.updateRole(role);
        return result;
    }
    @GetMapping("/findAll")
    public Result findAll(){
        return roleService.findAll();
    }

    @GetMapping("/count")
    public Result count(){
        return  roleService.count();
    }

    @GetMapping("/queryDetail")
    public Result queryDetail( Long roleId){
        Result result = roleService.queryDetail(roleId);
        return result;
    }


    @GetMapping("/queryRoleRel")
    public Result queryRoleRel(){
        Result result = roleService.queryRoleRel();
        return result;
    }

    @GetMapping("/queryRoleRelById")
    public Result queryRoleRelById( Long roleId){
        Result result = roleService.queryRoleRelById(roleId);
        return result;
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    public Result findByPage(@PathVariable int page,@PathVariable int pageSize){
        Result result = roleService.findByPage(page, pageSize);
        return result;
    }


}
