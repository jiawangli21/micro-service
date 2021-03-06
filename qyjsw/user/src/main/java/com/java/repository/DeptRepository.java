package com.java.repository;

import com.java.entity.TDept;
import com.java.entity.TDeptUserRel;
import com.java.vo.DeptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptRepository {

    /**
     * @desc 添加部门信息
     * @param dept
     * @return 返回部门内码
     */
    public int insert(TDept dept) throws Exception;

    /**
     * @desc  根据部门id 删除部门信息
     * @param deptId
     * @return 返回部门内码
     */
    public int deleteDept(Long deptId)  throws Exception;

    /**
     * @desc 删除部门用户关联信息
     * @param deptId
     */
    public void deleteDeptUserRel(Long deptId) throws Exception;
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
    public DeptVO queryById(Long deptId)  ;

    /**
     * @desc 通过部门名称查询，返回部门信息集合list
     * @param deptName
     * @return
     */
    public List<TDept> queryByName(String deptName);

    /**
     * @desc 通过部门名称查询，返回单个部门信息
     * @param deptName
     * @return
     */
    public TDept findByName(String deptName);


    /**
     * @desc 分页查询
     * @param start
     * @param pageSize
     * @return
     */
     public List<TDept> findByPage(@Param("start") int start, @Param("pageSize") int pageSize) ;

    /**
     * @desc 更新部门信息
     * @param dept
     */
     public void updateById(TDept dept);

    /**
     * @desc 查询与部门关联的用户关系表
     * @param deptId
     */
     public List<TDeptUserRel> queryDeptUserRel(Long deptId) ;
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
