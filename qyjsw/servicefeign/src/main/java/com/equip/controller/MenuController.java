package com.equip.controller;

import com.equip.entity.Result;
import com.equip.entity.TMenu;
import com.equip.po.MenuPO;
import com.equip.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 菜单信息管理
 * @author lijiawang
 * @dtae 2020/4/28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PutMapping("/addMenu")
    public Result addMenu(@RequestBody MenuPO menuPO){
        Result result = menuService.addMenu(menuPO);
        return result;
    }

    @DeleteMapping("/deleteMenu")
    public Result deleteMenu( Long menuId){
        Result result = menuService.deleteMenu(menuId);
        return result;
    }

    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody TMenu tMenu){
        Result result = menuService.updateMenu(tMenu);
        return result;
    }

    @GetMapping("/findAll")
    public Result findAll(){
        Result result = menuService.findAll();
        return result;
    }


    @GetMapping("/findUserMenu")
    public Result findUserMenu(Long userId){
        return menuService.findUserMenu(userId);
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    public Result findByPage(@PathVariable int page,@PathVariable int pageSize){
        Result result = menuService.findByPage(page,pageSize);
        return result;
    }

    /**
     * @desc 统计菜单数量
     * @return
     */
    @GetMapping("/count")
    public Result count(){
        return  menuService.count();
    }

}
