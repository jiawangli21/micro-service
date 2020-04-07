package com.htqyjsw1.controller;

import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.service.RoleService;
import com.htqyjsw1.vo.RoleVO;
import com.htqyjsw1.vo.RoleRelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 角色信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/role")
@Api(value="角色管理",tags={"角色信息操作接口"})
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping("/addRole")
    @ApiOperation(value = "添加角色信息")
    public String addRole(@RequestBody  RolePO role){

        String s = roleService.addRole(role);
        return s;
    }

    @DeleteMapping("/deleteRole")
    @ApiOperation(value = "删除角色信息")
    public String deleteRole(@ApiParam("角色编号") Long roleId){

        roleService.deleteRole(roleId);
        return "success";
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息")
    public String updateRole(@RequestBody RolePO role){
        String result = roleService.updateRole(role);
        return result;
    }


    @GetMapping("/queryDetail")
    @ApiOperation(value = "查看详情")
    public RoleVO queryDetail(@ApiParam("角色编号") Long roleId){

        RoleVO roleVO = roleService.queryDetail(roleId);
        return roleVO;
    }


    @GetMapping("/queryRoleRel")
    @ApiOperation(value = "获取角色关联信息" )
    public RoleRelVO queryRoleRel(){

        RoleRelVO roleRelVO = roleService.queryRoleRel();
        return roleRelVO;
    }



}
