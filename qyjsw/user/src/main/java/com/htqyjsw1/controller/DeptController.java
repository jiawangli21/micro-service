package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.repository.DeptRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.vo.DeptVO;
import com.htqyjsw1.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 部门信息管理
 * @author lijiawang
 * @dtae 2020/4/1
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    private static Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;


    /**
     * @desc 添加部门信息
     * @param dept
     * @return
     */
    @PutMapping("addDept")
    public String addDept(TDept dept){
        logger.info("【添加部门信息】，" + dept);
        int id = deptRepository.insert(dept);
        if(id < 0){
           return "false";
        }else{
            logger.info("添加部门信息失败"+ dept);
            return "success";
        }
    }

    /**
     * @desc 根据部门id 删除部门信息
     * @param deptId
     * @return
     */
    @DeleteMapping("/deleteDept")
    public String deleteDept(Integer deptId){
        deptService.deleteDept(deptId);
        return "success";
    }

    /**
     * @desc 统计部门数量
     * @return
     */
    @GetMapping("/count")
    public int count(){
        int count = deptRepository.count();
        logger.info("【统计部门数量】，数量 ：" + count);
        return count;
    }

    @GetMapping("/queryDetail")
    public DeptVO queryDetailById(Integer deptId){
        DeptVO deptVO = deptService.queryDetailById(deptId);
        return deptVO;
    }

    @GetMapping("/queryByName")
    public List<TDept> queryByName(String deptName){
        List<TDept>  dept = deptService.queryByName(deptName);
        return dept;
    }


    /**
     * @desc 分页查询
     * @return
     */
    @GetMapping("/findByPage")
    public PageVO queryByPage(int page, int pageSize){

        PageVO pageVO = deptService.findByPage(page, pageSize);

        return pageVO;
    }

    @PostMapping("/findByPage")
    public String updateDept(TDept dept){

       deptService.updateById(dept);

        return "success";
    }

}
