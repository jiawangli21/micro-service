package com.htqyjsw1.controller;


import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.service.FunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @desc 功能信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@Api(value="功能管理",tags={"功能信息操作接口"})
@RestController
@RequestMapping("/function")
public class FunctionController {


    @Autowired
    private FunctionService functionService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有")
    public Result findAll(){
        Result result = new Result(ResultStatusCode.OK);
        List<TFunction> tFunctionList = functionService.findAll();
        result.setData(tFunctionList);
        return result;
    }


    @ApiOperation(value = "添加功能信息")
    @PutMapping("/addFunction")
    public Result addFunction(@RequestBody TFunction tFunction){
        Result result = functionService.addFunction(tFunction);
        return result;
    }


    @ApiOperation(value = "删除功能信息")
    @DeleteMapping("/deleteFunction")
    public Result deleteFunction(@ApiParam("功能编号") Long funId){
        Result result = functionService.deleteFunction(funId);
        return result;
    }

    @PostMapping("/updateFunction")
    @ApiOperation(value = "更新功能信息")
    public Result updateFunction(@RequestBody TFunction tFunction, HttpServletRequest request){

        Result result = functionService.updateFunction(tFunction,request);
        return result;
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    @ApiOperation(value = "分页查询")
    public Result queryByPage(@PathVariable @ApiParam("当前页") int page,@PathVariable @ApiParam("每页显示的数据条数") int pageSize ){

        return functionService.queryByPage(page,pageSize);
    }

}
