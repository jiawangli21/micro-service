package com.equip.service;

import com.equip.vo.ExcelDateVo;
import com.equip.vo.FullTextSearchVo;
import com.equip.vo.QueryArticleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(value = "homepage")
public interface HomePageService {

    /**
     * 全文检索接口
     *
     * @param fullTextSearchVo
     * @return:检索结果
     */
    @PostMapping("/fullTextSearch")
    public Map<String, Object> fullTextSearch(@RequestBody FullTextSearchVo fullTextSearchVo);

    /**
     * 查询文档总量接口
     *
     * @return：文档总量
     */
    @PostMapping("/totalDocument")
    public Map<String, Object> totalDocument();

    /**
     * 文章查询接口
     *
     * @return：文档总量
     */
    @PostMapping("/queryArticle")
    public Map<String, Object> queryArticle(@RequestBody QueryArticleVo queryArticleVo);

    /**
     * 数据导入
     * @param file
     * @return
     */
    @PostMapping(value = "/file/upload",consumes =  "multipart/form-data")
    public Integer upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * 导出任务数据
     * @return
     */
    @PostMapping(value = "/file/download/job")
    public ExcelDateVo downloadJob();

    /**
     * 导出用户权限
     * @return
     */
    @PostMapping(value = "/file/download/user")
    public Map<String,ExcelDateVo> downloadUser();

    /**
     * 导出业务数据
     * @return
     */
    @PostMapping(value = "/file/download/data")
    public Map<String,ExcelDateVo> getBusnessData();

}
