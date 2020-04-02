package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.service.PageService;
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
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/findAll")
    public List<TPage> findAll(){

        List<TPage> tPageList = pageService.findAll();

        return tPageList;
    }
    @PutMapping("/addPage")
    public String addPage(TPage tPage){
        String result = pageService.addPage(tPage);
        return result;
    }

    @DeleteMapping("/deletePage")
    public String deletePage(Long pageId){
        String result = pageService.deletePage(pageId);
        return result;
    }

    @PostMapping("/updatePage")
    public String updatePage(TPage tPage){
        pageService.updatePage(tPage);
        return "success";
    }


}
