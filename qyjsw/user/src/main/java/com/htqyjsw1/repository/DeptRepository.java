package com.htqyjsw1.repository;

import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.entity.TDeptUserRel;
import com.htqyjsw1.vo.DeptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptRepository {

    /**
     * @desc 添加部门信息
     * @param dept
     * @return 返回部门内码
     */
    public int insert(TDept dept);

    /**
     * @desc  根据部门id 删除部门信息
     * @param deptId
     * @return 返回部门内码
     */
    public int deleteDept(Integer deptId);

    /**
     * @desc 删除部门用户关联信息
     * @param deptId
     */
    public void deleteDeptUserRel(Integer deptId);
    /**
     * @desc 统计部门数量
     * @return
     */
    public int count();

    /**
     * @desc 根据部门id，查询部门详情信息，包括部门成员的信息
     * @param deptId
     * @return DeptVO
     */
    public DeptVO queryById(Integer deptId);

    public List<TDept> queryByName(String deptName);

    /**
     * @desc 分页查询
     * @param start
     * @param pageSize
     * @return
     */
     public List<TDept> findByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @desc 更新部门信息
     * @param dept
     */
     public void updateById(TDept dept);

    /**
     * @desc 查询与部门关联的用户关系表
     * @param deptId
     */
     public List<TDeptUserRel> queryDeptUserRel(Integer deptId);
    /**
     * @desc 查询所有部门信息
     * @return List<TDept>
     */
     public List<TDept> findAll();
}
