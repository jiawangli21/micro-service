package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="菜单管理",tags={"菜单信息操作接口"})
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PutMapping("/addMenu")
    @ApiOperation(value = "添加菜单信息")
    public String addMenu(@RequestBody TMenu tMenu){
        String result = menuService.addMenu(tMenu);
        return result;
    }

    @DeleteMapping("/deleteMenu")
    @ApiOperation(value = "删除菜单信息")
    public String deleteMenu(@ApiParam("菜单编号") Long menuId){
        String result = menuService.deleteMenu(menuId);
        return result;
    }

    @PostMapping("/updateMenu")
    @ApiOperation(value = "更新菜单信息")
    public String updateMenu(@RequestBody TMenu tMenu){
        menuService.updateMenu(tMenu);
        return "success";
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有")
    public List<TMenu> findAll(){
        List<TMenu> tMenuList = menuService.findAll();

        return tMenuList;
    }

}
