package com.htqyjsw1.controller;

import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.service.RoleService;
import com.htqyjsw1.vo.RoleVO;
import com.htqyjsw1.vo.RoleRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 角色信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping("/addRole")
    public String addRole(@RequestBody  RolePO role){

        String s = roleService.addRole(role);
        return s;
    }

    @DeleteMapping("/deleteRole")
    public String deleteRole(Long roleId){

        roleService.deleteRole(roleId);
        return "success";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestBody RolePO role){
        String result = roleService.updateRole(role);
        return result;
    }


    @GetMapping("/queryDetail")
    public RoleVO queryDetail(Long roleId){

        RoleVO roleVO = roleService.queryDetail(roleId);
        return roleVO;
    }


    @GetMapping("/queryRoleRel")
    public RoleRelVO queryRoleRel(){

        RoleRelVO roleRelVO = roleService.queryRoleRel();
        return roleRelVO;
    }



}
