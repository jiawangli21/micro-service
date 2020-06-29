package com.equip.controller;


import com.equip.entity.Result;
import com.equip.entity.TRight;
import com.equip.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 权限信息管理
 * @author lijiawang
 * @dtae 2020/4/28
 */
@RestController
@RequestMapping("/right")
public class RightController {


    @Autowired
    private RightService rightService;

    @PutMapping("/addRight")
    public Result addRight(@RequestBody TRight tRight){
        Result result = rightService.addRight(tRight);
        return result;
    }

    @DeleteMapping("/deleteRight")
    public Result deleteRight(Long rightId){
        Result result = rightService.deleteRight(rightId);
        return result;
    }

    @PostMapping("/updateRight")
    public Result updateRight(@RequestBody TRight tRight){
        Result result = rightService.updateRight(tRight);
        return result;
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    public Result findByPage(@PathVariable int page,@PathVariable int pageSize){
        Result result = rightService.findByPage(page,pageSize);
        return result;
    }

    @GetMapping("/findById")
    public Result findById(Long rightId){

        return rightService.findById(rightId);
    }

    @GetMapping("/count")
    public Result count(){
        return  rightService.count();
    }
}
