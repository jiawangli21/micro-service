package com.equip.service;


import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.entity.TRight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface RightService {

    /**
     * @desc 添加权限信息
     * @param tRight
     * @return
     */
    @RequestMapping(value = "/right/addRight",method = RequestMethod.PUT)
    public Result addRight(TRight tRight);

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    @RequestMapping(value = "/right/deleteRight",method = RequestMethod.DELETE)
    public Result deleteRight(@RequestParam("rightId") Long rightId);

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    @RequestMapping(value = "/right/updateRight",method = RequestMethod.POST)
    public Result updateRight(TRight tRight);

    /**
     * @desc 通过id查询权限
     * @param rightId
     * @return
     */
    @RequestMapping(value = "/right/findById",method = RequestMethod.GET)
    public Result findById(@RequestParam("rightId") Long rightId);

    /**
     * @desc 分页查询权限信息
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/right/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result findByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    /**
     * @desc 统计权限数量
     * @return
     */
    @RequestMapping(value = "/right/count",method = RequestMethod.GET)
    public Result count();

}
