package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
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
    public Result addRole(@RequestBody  RolePO role){
        Result result = roleService.addRole(role);
        return result;
    }

    @DeleteMapping("/deleteRole")
    @ApiOperation(value = "删除角色信息")
    public Result deleteRole(@ApiParam("角色编号") Long roleId){
        Result result = roleService.deleteRole(roleId);
        return result;
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息")
    public Result updateRole(@RequestBody RolePO role){
        Result result = roleService.updateRole(role);
        return result;
    }


    @GetMapping("/queryDetail")
    @ApiOperation(value = "查看详情")
    public Result queryDetail(@ApiParam("角色编号") Long roleId){
        Result result = roleService.queryDetail(roleId);
        return result;
    }


    @GetMapping("/queryRoleRel")
    @ApiOperation(value = "获取角色关联信息" )
    public Result queryRoleRel(){
        Result result = roleService.queryRoleRel();
        return result;
    }


}
