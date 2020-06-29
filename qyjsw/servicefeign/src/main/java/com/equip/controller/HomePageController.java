package com.equip.controller;

import com.equip.service.HomePageService;
import com.equip.util.ResponseBo;
import com.equip.util.StringUtils;
import com.equip.vo.ExcelDateVo;
import com.equip.vo.FullTextSearchVo;
import com.equip.vo.QueryArticleVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/homePage")
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    /**
     * 全文检索接口
     *
     * @param fullTextSearchVo
     * @return:检索结果
     */
    @PostMapping("/fullTextSearch")
    public Map<String, Object> fullTextSearch(@RequestBody FullTextSearchVo fullTextSearchVo) {
        if (fullTextSearchVo.getKeyWord() == null || fullTextSearchVo.getKeyWord().length() == 0) {
            return ResponseBo.error("搜索关键词不能为空");
        }
        return ResponseBo.ok("成功", homePageService.fullTextSearch(fullTextSearchVo));
    }

    /**
     * 查询文档总量接口
     *
     * @return：文档总量
     */
    @PostMapping("/totalDocument")
    public Map<String, Object> totalDocument() {
        return ResponseBo.ok("成功", homePageService.totalDocument());
    }

    /**
     * 文章查询接口
     *
     * @return：文档总量
     */
    @PostMapping("/queryArticle")
    public Map<String, Object> queryArticle(@RequestBody QueryArticleVo queryArticleVo) {
        if (queryArticleVo.getDataId() == 0) {
            return ResponseBo.error("搜索ID不能为空");
        }
        return ResponseBo.ok("成功", homePageService.queryArticle(queryArticleVo));
    }

    /**
     * 数据导入
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseBo upload(@RequestPart(value = "file") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (!name.endsWith("xlsx") && !name.endsWith("xls")) {

            return ResponseBo.error("文件格式错误");
        }

        Integer count = homePageService.upload(file);
        if(count >= 0){
            return ResponseBo.ok("成功", count);
        }else {
            return ResponseBo.error("文件格式错误");
        }
    }

    /**
     * 数据导出
     * @param response
     * @return
     */
    @GetMapping(value = "/download")
    public ResponseBo download(HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        String fileName = "data.xls";

        HSSFSheet sheet = workbook.createSheet("t_sk_job_instance");
        ExcelDateVo job = homePageService.downloadJob();
        //定时任务
        writeExcel(sheet,job);
        HSSFSheet sheet2 = workbook.createSheet("t_user");
        HSSFSheet sheet3 = workbook.createSheet("t_role");
        HSSFSheet sheet4 = workbook.createSheet("t_right");
        HSSFSheet sheet5 = workbook.createSheet("t_user_role_rel");
        HSSFSheet sheet6 = workbook.createSheet("t_role_right_rel");
        Map<String,ExcelDateVo> userMap = homePageService.downloadUser();
        ExcelDateVo user = userMap.get("user");
        ExcelDateVo role = userMap.get("role");
        ExcelDateVo right = userMap.get("right");
        ExcelDateVo userRoleRel = userMap.get("userRoleRel");
        ExcelDateVo roleRightRel = userMap.get("roleRightRel");
        //用户权限
        writeExcel(sheet2,user);
        writeExcel(sheet3,role);
        writeExcel(sheet4,right);
        writeExcel(sheet5,userRoleRel);
        writeExcel(sheet6,roleRightRel);
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return ResponseBo.error("失败");
        }
    }


    private void writeExcel(HSSFSheet sheet,ExcelDateVo job ){
        int rowNum = 1;
        List<String> header = job.getTitle();

        //设置表头
        HSSFRow row = sheet.createRow(0);
        for(int i = 0 ; i < header.size() ; i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header.get(i));
            cell.setCellValue(text);
        }
        //组装内容
        for(Object o:job.getList()){
            LinkedHashMap<String,String> linkedHashMap  = (LinkedHashMap) o;
            Row rowNext = sheet.createRow(rowNum);
            for(int i = 0 ; i < sheet.getRow(0).getLastCellNum(); i++){
                String head = sheet.getRow(0).getCell(i).getStringCellValue();

                String key = StringUtils.underline2Camel(head);
                Cell cell = rowNext.createCell(i);
                String value = String.valueOf(linkedHashMap.get(key));
                cell.setCellValue(value);
            }
            rowNum++;
        }
    }

}
