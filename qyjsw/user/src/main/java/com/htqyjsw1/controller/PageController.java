package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 页面信息管理
 * @author lijiawang
 * @dtae 2020/4/2
 */
@RestController
@RequestMapping("/page")
@Api(value="页面管理",tags={"页面信息操作接口"})
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有")
    public Result findAll(){
        Result result = new Result(ResultStatusCode.OK);
        List<TPage> list = pageService.findAll();
        result.setData(list);
        return result;
    }

    @PutMapping("/addPage")
    @ApiOperation(value = "添加页面信息")
    public Result addPage(@RequestBody TPage tPage){
        Result result = pageService.addPage(tPage);
        return result;
    }

    @DeleteMapping("/deletePage")
    @ApiOperation(value = "删除页面信息")
    public Result deletePage(@ApiParam("页面编号") Long pageId){
        Result result = pageService.deletePage(pageId);
        return result;
    }

    @PostMapping("/updatePage")
    @ApiOperation(value = "更新页面信息")
    public Result updatePage(@RequestBody TPage tPage){
        Result result = pageService.updatePage(tPage);
        return result;
    }


    @GetMapping("/findByPage/{page}/{pageSize}/")
    @ApiOperation(value = "分页查询")
    public Result queryByPage(@PathVariable @ApiParam("当前页") int page,@PathVariable @ApiParam("每页显示的数据条数") int pageSize ){

        return pageService.queryByPage(page,pageSize);
    }
}
