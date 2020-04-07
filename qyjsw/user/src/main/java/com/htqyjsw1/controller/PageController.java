package com.htqyjsw1.controller;

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
    public List<TPage> findAll(){

        List<TPage> tPageList = pageService.findAll();

        return tPageList;
    }
    @PutMapping("/addPage")
    @ApiOperation(value = "添加页面信息")
    public String addPage(@RequestBody TPage tPage){
        String result = pageService.addPage(tPage);
        return result;
    }

    @DeleteMapping("/deletePage")
    @ApiOperation(value = "删除页面信息")
    public String deletePage(@ApiParam("页面编号") Long pageId){
        String result = pageService.deletePage(pageId);
        return result;
    }

    @PostMapping("/updatePage")
    @ApiOperation(value = "更新页面信息")
    public String updatePage(@RequestBody TPage tPage){
        pageService.updatePage(tPage);
        return "success";
    }


}
