package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.repository.DeptRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.vo.DeptVO;
import com.htqyjsw1.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="部门管理",tags={"部门信息操作接口"})
public class DeptController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;


    /**
     * @desc 添加部门信息
     * @param dept
     * @return
     */
    @ApiOperation(value = "添加部门信息")
    @PutMapping("addDept")
    public String addDept(@RequestBody TDept dept){
        logger.info("【添加部门信息】，" + dept);
        int id = deptRepository.insert(dept);
        System.out.println("deptId = "+id);
        if(id < 0){
           return "false";
        }else{
            logger.info("【添加部门信息成功！】，dept:"+ dept);
            return "success";
        }
    }

    /**
     * @desc 根据部门id 删除部门信息
     * @param deptId
     * @return
     */
    @DeleteMapping("/deleteDept")
    @ApiOperation(value = "删除部门信息")
    public String deleteDept(@ApiParam("部门编号") Integer deptId){
        deptService.deleteDept(deptId);
        return "success";
    }

    /**
     * @desc 统计部门数量
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "统计部门数量")
    public int count(){
        int count = deptRepository.count();
        logger.info("【统计部门数量】，数量 ：" + count);
        return count;
    }

    @GetMapping("/queryDetail")
    @ApiOperation(value = "查看部门详情")
    public DeptVO queryDetailById(@ApiParam("部门编号") Integer deptId){
        DeptVO deptVO = deptService.queryDetailById(deptId);
        return deptVO;
    }

    @GetMapping("/queryByName")
    @ApiOperation(value = "根据部门名称查询")
    public List<TDept> queryByName(@ApiParam("部门名称") String deptName){
        List<TDept>  dept = deptService.queryByName(deptName);
        return dept;
    }


    /**
     * @desc 分页查询
     * @return
     */
    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查询部门信息")
    public PageVO queryByPage(@ApiParam("当前页数") int page,@ApiParam("每页显示的数据条数") int pageSize){

        PageVO pageVO = deptService.findByPage(page, pageSize);

        return pageVO;
    }

    @PostMapping("/updateDept")
    @ApiOperation(value = "更新部门信息")
    public String updateDept(@RequestBody TDept dept){

       deptService.updateById(dept);

        return "success";
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有部门信息")
    public List<TDept> findAll(){
        List<TDept> tDeptList = deptService.findAll();
        return tDeptList;
    }
}
