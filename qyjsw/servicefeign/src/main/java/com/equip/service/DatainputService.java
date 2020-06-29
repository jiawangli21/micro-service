package com.equip.service;

import com.equip.entity.Backup;
import com.equip.page.Page;
import com.equip.vo.Upload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("datainput")
public interface DatainputService {

    /**
     * 文件上传
     * @param fileType
     * @param file
     * @return
     */
    @RequestMapping("/data/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("fileType") String fileType,@RequestParam("file") MultipartFile file);

    /**
     * 分页查找文件
     * @param page
     * @param type
     * @param key
     * @return
     */
    @RequestMapping(value = "/data/queryAll")
    public Page<Upload> queryAllUpload(@RequestParam("page")Page page,@RequestParam("type") String type, @RequestParam("key")String key);

    /**
     * 读取文件
     * @param id
     * @return
     */
    @RequestMapping("/data/getFile")
    public List<?> getFileContent(Long id);

    /**
     * 数据库备份
     * @return
     */
    @RequestMapping("/backup/start")
    public int backUpData();

    /**
     * 查询所有备份
     * @param page
     * @param backup
     * @return
     */
    @RequestMapping(value="backup/queryAll")
    public Page<Backup> queryBackUp(@RequestParam("page")Page page,@RequestParam("bcakup")Backup backup);

    /**
     * 恢复备份
     * @param id
     */
    @RequestMapping("backup/resume")
    public void resumeData(Long id);

}
