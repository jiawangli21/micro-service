package com.equip.controller;

import com.equip.entity.Result;
import com.equip.po.UserPO;
import com.equip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 用户信息管理类
 * @date 2020/4/27
 * @author lijiawang
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PutMapping("/addUser")
    public Result addUser(@RequestBody UserPO user){
        Result result = userService.addUser(user);
        return result;
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UserPO user){
        Result result = userService.updateUser(user);
        return result;
    }

    @DeleteMapping("/delete")
    public Result deleteUser(Long userId){
        Result result = userService.deleteUser(userId);
        return result;
    }

    @GetMapping("/count")
    public Result count(){
        Result result =  userService.count();
        return result;
    }

     @GetMapping("/findByPage/{page}/{pageSize}/")
     public Result queryByPage(@PathVariable int page,@PathVariable int pageSize ){

        return userService.queryByPage(page,pageSize);
     }

     @GetMapping("/findById")
     public Result findById( Long userId){
         Result result = userService.findById(userId);
         return result;
     }

    @GetMapping("/findByName")
    public Result findByName( String userName){
        Result result = userService.findByName(userName);
        return result;
    }

    /**
     * @desc 获取用户所拥有的权限信息
     * @param userId
     * @return
     */

    @GetMapping("/findUserRight")
    public Result findUserRight(@RequestParam Long userId){
        return userService.findUserRight(userId);
    }


    @GetMapping("/findAll")
    public Result findAll(){
        Result result = userService.findAll();
        return result;
    }
}
