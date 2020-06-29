package com.equip.controller;

import com.equip.entity.Result;
import com.equip.entity.TFunction;
import com.equip.po.FunctionPO;
import com.equip.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 功能信息管理
 * @author lijiawang
 * @dtae 2020/4/29
 */
@RestController
@RequestMapping("/function")
public class FunctionController {


    @Autowired
    private FunctionService functionService;

    @GetMapping("/findAll")
    public Result findAll(){
        Result result  = functionService.findAll();
        return result;
    }

    @PutMapping("/addFunction")
    public Result addFunction(@RequestBody FunctionPO functionPO){
        Result result = functionService.addFunction(functionPO);
        return result;
    }

    @DeleteMapping("/deleteFunction")
    public Result deleteFunction(Long funId){
        Result result = functionService.deleteFunction(funId);
        return result;
    }

    @PostMapping("/updateFunction")
    public Result updateFunction(@RequestBody TFunction tFunction){
        Result result = functionService.updateFunction(tFunction);
        return result;
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    public Result queryByPage(@PathVariable int page,@PathVariable int pageSize ){
        return functionService.queryByPage(page,pageSize);
    }

    @GetMapping("/count")
    public Result count(){
        return  functionService.count();
    }
}
