package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.repository.DeptRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 部门信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/dept")
@Api(value="部门管理",tags={"部门信息操作接口"})
public class DeptController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;


    @ApiOperation(value = "添加部门信息")
    @PutMapping("/addDept")
    public Result addDept(@RequestBody TDept dept){

        Result result = deptService.addDept(dept);
        return  result;
    }


    @DeleteMapping("/deleteDept")
    @ApiOperation(value = "删除部门信息")
    public Result deleteDept(@ApiParam("部门编号") Long deptId){
        return  deptService.deleteDept(deptId);
    }

    @GetMapping("/count")
    @ApiOperation(value = "统计部门数量")
    public Result count(){
        Result result = new Result(ResultStatusCode.OK);
        int totalSize = deptRepository.count();
        logger.info("【统计部门数量】，数量 ：" + totalSize);
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
        return result;
    }


    @GetMapping("/queryDetail")
    @ApiOperation(value = "查看部门详情")
    public Result queryDetailById(@ApiParam("部门编号") Long deptId){
        Result result = deptService.queryDetailById(deptId);
        return result;
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "根据部门名称查询")
    public Result findByName(@ApiParam("部门名称") String deptName){
        Result  result = deptService.queryByName(deptName);
        return result;
    }


    @GetMapping("/findByPage/{page}/{pageSize}/")
    @ApiOperation(value = "分页查询部门信息")
    public Result queryByPage(@PathVariable @ApiParam("当前页") int page,@PathVariable @ApiParam("每页显示的数据条数") int pageSize ){
        Result result = deptService.findByPage(page,pageSize);
        return result;
    }

    @PostMapping("/updateDept")
    @ApiOperation(value = "更新部门信息")
    public Result updateDept(@RequestBody TDept dept){
        Result result =  deptService.updateById(dept);
        return result;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有部门信息")
    public Result findAll(){
        Result result = new Result(ResultStatusCode.OK);
        List<TDept> tDeptList = deptService.findAll();
        result.setData(tDeptList);
        return result;
    }
}
