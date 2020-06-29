package com.equip.controller;

import com.equip.entity.Result;
import com.equip.entity.TDept;
import com.equip.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 部门信息管理
 * @author lijiawang
 * @dtae 2020/4/29
 */
@RestController
@RequestMapping("/dept")
public class DeptController {


    @Autowired
    private DeptService deptService;

    @PutMapping("/addDept")
    public Result addDept(@RequestBody TDept dept){
        Result result = deptService.addDept(dept);
        return  result;
    }

    @DeleteMapping("/deleteDept")
    public Result deleteDept(Long deptId){
        return  deptService.deleteDept(deptId);
    }

    @GetMapping("/count")
    public Result count(){
        Result result = deptService.count();
        return result;
    }


    @GetMapping("/queryDetail")
    public Result queryDetailById(Long deptId){
        Result result = deptService.queryDetailById(deptId);
        return result;
    }

    @GetMapping("/findByName")
    public Result findByName(String deptName){
        Result  result = deptService.queryByName(deptName);
        return result;
    }


    @GetMapping("/findByPage/{page}/{pageSize}/")
    public Result queryByPage(@PathVariable int page,@PathVariable int pageSize ){
        Result result = deptService.findByPage(page,pageSize);
        return result;
    }

    @PostMapping("/updateDept")
    public Result updateDept(@RequestBody TDept dept){
        Result result =  deptService.updateById(dept);
        return result;
    }

    @GetMapping("/findAll")
    public Result findAll(){
        Result result = deptService.findAll();
        return result;
    }
}
