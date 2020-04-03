package com.htqyjsw1.repository;

import com.htqyjsw1.entity.TDeptUserRel;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserRepository {

    /**
     * @desc 添加用户信息
     * @param user
     * @return 插入用户信息的主键
     */
    public Long insert(UserPO user) throws Exception;

    /**
     * @desc 根据用户id 删除用户信息
     * @param userId
     * @return
     */
    public int delete(Long userId);

    /**
     * @desc 根据用户id删除部门用户所属关系
     * @param userId
     */
    public void deleteDeptUserRel(Long userId);

    /**
     * @desc 删除用户拥有的角色信息
     * @param userId
     */
    public void  deleteUserRoleRel(Long userId);

    /**
     * @desc 统计所有用户数量
     * @return
     */
    public int count();

    /**
     * @desc 查找所有用户信息
     * @return
     */
    public List<TUser> findAll();

    /**
     * @desc 分页查找
     * @return
     */
    public List<TUser> findByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @desc 根据用户id查找用户信息
     * @param userId
     * @return
     */
    public UserVO findById(Long userId);

    /**
     * @desc 根据用户名称查找用户信息
     * @param userName
     * @return
     */
    public List<TUser> findByName(@Param("userName") String  userName);

    /**
     * @desc 查询部门相关用户信息
     * @param deptId
     * @return list
     */
    public List<TUser> queryUserByDeptId(Integer deptId);

    public List<TUser> queryUserByRoleId(Long roleId);

    /**
     * @desc 插入（部门-用户）关联信息
     * @param deptId
     * @param userId
     */
    public void insertDeptUserRel(@Param("deptId") Long deptId,@Param("userId") Long userId) throws Exception;

    /**
     * @desc 插入（ 用户-角色 ）关联信息
     * @param roleId
     * @param userId
     */
    public void insertUserRoleRel(@Param("userId") Long userId,@Param("roleId") Long roleId) throws Exception;

    /**
     * @desc 根据用户id 更新用户信息
     * @param user
     */
    public void update(UserPO user);
}
