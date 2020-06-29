package com.equip.service;

import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.entity.TDept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface DeptService {

    /**
     * @desc 添加部门信息
     * @param dept
     * @return
     */
    @RequestMapping(value = "/dept/addDept",method = RequestMethod.PUT)
    public Result addDept(TDept dept);

    /**
     * @desc 根据部门id，查询部门详情信息，包括部门成员的信息
     * @param deptId
     * @return DeptVO
     */
    @RequestMapping(value = "/dept/queryDetail",method = RequestMethod.GET)
    public Result queryDetailById(@RequestParam("deptId") Long deptId);


    /**
     * @desc 根据部门名称查询部门信息
     * @param deptName
     * @return Dept
     */
    @RequestMapping(value = "/dept/findByName",method = RequestMethod.GET)
    public Result queryByName(@RequestParam("deptName") String deptName);

    /**
     * @desc 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/dept/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result findByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    /**
     * @desc 更新部门信息
     * @param dept
     */
    @RequestMapping(value = "/dept/updateDept",method = RequestMethod.POST)
    public Result updateById(TDept dept);

    /**
     * @desc  根据部门id 删除部门信息
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/dept/deleteDept",method = RequestMethod.DELETE)
    public Result deleteDept(@RequestParam("deptId") Long deptId);

    /**
     * @desc 查询所有部门信息
      * @return List<TDept>
     */
    @RequestMapping(value = "/dept/findAll",method = RequestMethod.GET)
    public Result findAll();

    @RequestMapping(value = "/dept/count",method = RequestMethod.GET)
    public Result count();
}
