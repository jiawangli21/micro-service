package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TRight;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.service.RightService;
import com.htqyjsw1.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 权限信息管理
 * @author lijiawang
 * @dtae 2020/4/2
 */
@RestController
@RequestMapping("/right")
@Api(value="权限管理",tags={"权限信息操作接口"})
public class RightController {


    @Autowired
    private RightService rightService;

    @PutMapping("/addRight")
    @ApiOperation(value = "添加权限信息")
    public String addRight(@RequestBody TRight tRight){
        String result = rightService.addRight(tRight);
        return result;
    }

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    @DeleteMapping("/deleteRight")
    @ApiOperation(value = "删除权限信息")
    public String deleteRight(@ApiParam("权限编号") Long rightId){
        String result = rightService.deleteRight(rightId);
        return result;
    }

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    @PostMapping("/updateRight")
    @ApiOperation(value = "修改权限信息")
    public String updateRight(@RequestBody TRight tRight){
        rightService.updateRight(tRight);
        return "success";
    }

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查询权限信息")
    public List<TRight> findByPage(){

        return null;
    }
}
