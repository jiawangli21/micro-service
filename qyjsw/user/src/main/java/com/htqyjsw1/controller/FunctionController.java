package com.htqyjsw1.controller;


import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 功能信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/function")
public class FunctionController {


    @Autowired
    private FunctionService functionService;

    @GetMapping("/findAll")
    public List<TFunction> findAll(){
        List<TFunction> tFunctionList = functionService.findAll();
        return tFunctionList;
    }

    /**
     * @desc 添加功能信息
     * @param tFunction
     * @return
     */
    @PutMapping("/addFunction")
    public String addFunction(TFunction tFunction){
        String result = functionService.addFunction(tFunction);
        return result;
    }

    /**
     * @desc 删除功能信息
     * @param funId
     * @return
     */
    @DeleteMapping("/deleteFunction")
    public String deleteFunction(Long funId){
        String result = functionService.deleteFunction(funId);
        return result;
    }

    /**
     * @desc 更新功能信息
     * @param tFunction
     * @return
     */
    @PostMapping("/updateFunction")
    public String updateFunction(TFunction tFunction){
         functionService.updateFunction(tFunction);
        return "success";
    }

}
