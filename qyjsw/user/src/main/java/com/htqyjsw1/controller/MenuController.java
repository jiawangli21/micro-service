package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.po.MenuPO;
import com.htqyjsw1.service.MenuService;
import com.htqyjsw1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result addMenu(@RequestBody MenuPO menuPO){
        Result result = menuService.addMenu(menuPO);
        return result;
    }

    @DeleteMapping("/deleteMenu")
    @ApiOperation(value = "删除菜单信息")
    public Result deleteMenu(@ApiParam("菜单编号") Long menuId, HttpServletRequest request){
        Result result = menuService.deleteMenu(menuId,request);
        return result;
    }

    @PostMapping("/updateMenu")
    @ApiOperation(value = "更新菜单信息")
    public Result updateMenu(@RequestBody TMenu tMenu, HttpServletRequest request){
        Result result = menuService.updateMenu(tMenu,request);
        return result;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有菜单信息")
    public Result findAll(){
        Result result = new Result(ResultStatusCode.OK);
        List<TMenu> tMenuList = menuService.findAll();
        result.setData(tMenuList);
        return result;
    }


    @GetMapping("/findUserMenu")
    @ApiOperation("查询用户可以加载哪些菜单")
    public Result findUserMenu(@ApiParam("用户编号") Long userId){

        return menuService.findUserMenu(userId);
    }

    @GetMapping("/findByPage/{page}/{pageSize}/")
    @ApiOperation(value = "分页查询菜单信息")
    public Result findByPage(@PathVariable @ApiParam("当前页数") int page,@PathVariable @ApiParam("每页显示的数据条数") int pageSize){
        Result result = menuService.findByPage(page,pageSize);
        return result;
    }

    /**
     * @desc 统计菜单数量
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "统计菜单数量")
    public Result count(){
        Result result = new Result(ResultStatusCode.OK);
        int totalSize =  menuService.count();
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
        return result;
    }

}
