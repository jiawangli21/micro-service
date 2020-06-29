package com.equip.service;

import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.entity.TFunction;
import com.equip.po.FunctionPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface FunctionService {


    /**
     * @desc 查询所有功能信息
     * @return List<TFunction>
     */
    @RequestMapping(value = "/function/findAll",method = RequestMethod.GET)
    public Result findAll();


    /**
     * @desc 添加功能信息
     * @param functionPO
     * @return
     */
    @RequestMapping(value = "/function/addFunction",method = RequestMethod.PUT)
    public Result addFunction(FunctionPO functionPO);

    /**
     * @desc 删除功能信息
     * @param funId
     * @return
     */
    @RequestMapping(value = "/function/deleteFunction",method = RequestMethod.DELETE)
    public Result deleteFunction(@RequestParam("funId") Long funId);

    /**
     * @desc 更新功能信息
     * @param tFunction
     * @return
     */
    @RequestMapping(value = "/function/updateFunction",method = RequestMethod.POST)
    public Result updateFunction(TFunction tFunction);

    @RequestMapping(value = "/function/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result queryByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    @RequestMapping(value = "/function/count",method = RequestMethod.GET)
    public Result count();

}
