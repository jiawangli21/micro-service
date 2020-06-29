package com.java.controller;

import com.java.entity.Result;
import com.java.entity.TRight;
import com.java.service.RightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result addRight(@RequestBody TRight tRight){
        Result result = rightService.addRight(tRight);
        return result;
    }

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    @DeleteMapping("/deleteRight")
    @ApiOperation(value = "删除权限信息")
    public Result deleteRight(@ApiParam("权限编号") Long rightId){
        Result result = rightService.deleteRight(rightId);
        return result;
    }

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    @PostMapping("/updateRight")
    @ApiOperation(value = "修改权限信息")
    public Result updateRight(@RequestBody TRight tRight){
        Result result = rightService.updateRight(tRight);
        return result;
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    @ApiOperation(value = "分页查询权限信息")
    public Result findByPage(@PathVariable @ApiParam("当前页数") int page,@PathVariable @ApiParam("每页显示的数据条数") int pageSize){
        Result result = rightService.findByPage(page,pageSize);
        return result;
    }


    @GetMapping("/findById")
    @ApiOperation(value = "通过id查询权限")
    public Result findById(Long rightId){

        return rightService.findById(rightId);
    }


    @GetMapping("/count")
    @ApiOperation(value = "统计权限数量")
    public Result count(){
        return  rightService.count();
    }
}
