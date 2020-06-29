package com.equip.service;


import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.entity.TMenu;
import com.equip.po.MenuPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface MenuService {

    /**
     * @desc 添加菜单
     * @param menuPO
     * @return
     */
    @RequestMapping(value = "/menu/addMenu",method = RequestMethod.PUT)
    public Result addMenu(MenuPO menuPO);

    /**
     * @desc 删除菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/menu/deleteMenu",method = RequestMethod.DELETE)
    public Result deleteMenu(@RequestParam("menuId") Long menuId);

    /**
     * @desc 更新菜单信息
     * @param tMenu
     */
    @RequestMapping(value = "/menu/updateMenu",method = RequestMethod.POST)
    public Result updateMenu(TMenu tMenu);

    /**
     * @desc 查询所有菜单信息
     */
    @RequestMapping(value = "/menu/findAll",method = RequestMethod.GET)
    public Result findAll();

    /**
     * @desc 查询用户关联的菜单信息，返回菜单集合
     * @param userId
     * @return
     */
    @RequestMapping(value = "/menu/findUserMenu",method = RequestMethod.GET)
    public Result findUserMenu(@RequestParam("userId") Long userId);

    /**
     * @desc 分页查询菜单信息
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/menu/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result findByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    @RequestMapping(value = "/menu/count",method = RequestMethod.GET)
    public Result count();

}
