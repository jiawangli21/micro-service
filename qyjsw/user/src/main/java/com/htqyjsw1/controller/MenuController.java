package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 菜单信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PutMapping("/addMenu")
    public String addMenu(TMenu tMenu){
        String result = menuService.addMenu(tMenu);
        return result;
    }

    @DeleteMapping("/deleteMenu")
    public String deleteMenu(Long menuId){
        String result = menuService.deleteMenu(menuId);
        return result;
    }

    @PostMapping("/updateMenu")
    public String updateMenu(TMenu tMenu){
        menuService.updateMenu(tMenu);
        return "success";
    }

    @GetMapping("/findAll")
    public List<TMenu> findAll(){
        List<TMenu> tMenuList = menuService.findAll();

        return tMenuList;
    }

}
