package com.equip.controller;

import com.equip.entity.Backup;
import com.equip.page.Page;
import com.equip.service.DatainputService;
import com.equip.service.HomePageService;
import com.equip.util.ResponseBo;
import com.equip.util.StringUtils;
import com.equip.vo.ExcelDateVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    HomePageService homePageService;
    @Autowired
    DatainputService datainputService;


    /**
     * 数据导入(业务数据)
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseBo upload(@RequestPart(value = "file") MultipartFile file,String type) {
        String name = file.getOriginalFilename();
        if (!name.endsWith("xlsx") && !name.endsWith("xls")) {
            return ResponseBo.error("文件格式错误");
        }
        if("oracle".equals(type)){
            int count = homePageService.upload(file);

            return ResponseBo.ok("成功",null);
        }else if("txt".equals(type) || "csv".equals(type) || "excel".equals(type)){
            int count = homePageService.upload(file);
            if(count > 0){
                String url = datainputService.uploadFile(type,file);
                return ResponseBo.ok("成功",url);
            }
        }else{
            return ResponseBo.error("保存类型错误");
        }
        return ResponseBo.error("系统错误");

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

    @GetMapping(value = "/backup")
    public ResponseBo backUp(){
        datainputService.backUpData();
        return ResponseBo.ok("备份成功");
    }
    @PostMapping(value = "/resume")
    public ResponseBo resume(Long id){
        if(null == id || 0 == id){
            return ResponseBo.error("数据错误");
        }
        datainputService.resumeData(id);
        return ResponseBo.ok("恢复备份完成");
    }
    @GetMapping(value = "/query")
    public ResponseBo queryBackup(Page page, Backup backup){

        Page<Backup> pageList = datainputService.queryBackUp(page, backup);

        return ResponseBo.ok("成功",pageList);
    }
}
