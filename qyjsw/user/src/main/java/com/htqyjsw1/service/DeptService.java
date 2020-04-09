package com.htqyjsw1.service;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.vo.DeptVO;
import com.htqyjsw1.vo.PageVO;

import java.util.List;

public interface DeptService {

    /**
     * @desc 添加部门信息
     * @param dept
     * @return
     */
    public Result addDept(TDept dept);

    /**
     * @desc 根据部门id，查询部门详情信息，包括部门成员的信息
     * @param deptId
     * @return DeptVO
     */
    public Result queryDetailById(Integer deptId);


    /**
     * @desc 根据部门名称查询部门信息
     * @param deptName
     * @return Dept
     */
    public Result queryByName(String deptName);

    /**
     * @desc 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public Result findByPage(int page, int pageSize);

    /**
     * @desc 更新部门信息
     * @param dept
     */
    public Result updateById(TDept dept);

    /**
     * @desc  根据部门id 删除部门信息
     * @param deptId
     * @return
     */
    public Result deleteDept(Integer deptId);

    /**
     * @desc 查询所有部门信息
      * @return List<TDept>
     */
    public List<TDept> findAll();

    /**
     * @desc 根据用户id查询用户所属部门信息，返回部门集合
     * @param userId
     * @return List<TDept>
     */
    public  List<TDept> queryDeptByUserId(Long userId);

}
