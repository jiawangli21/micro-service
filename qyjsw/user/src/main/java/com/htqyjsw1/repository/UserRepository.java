package com.htqyjsw1.repository;

import com.htqyjsw1.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserRepository {

    /**
     * @desc 添加用户信息
     * @param user
     * @return 插入用户信息的主键
     */
    public int insert(TUser user);

    public int insertSelective(TUser user);

    /**
     * @desc 根据用户id 删除用户信息
     * @param userId
     * @return
     */
    public int deleteById(Integer userId);

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
    public TUser findById(Integer userId);

    /**
     * @desc 根据用户名称查找用户信息
     * @param userName
     * @return
     */
    public List<TUser> findByName(@Param("userName") String  userName);

    /**
     * @desc 根据用户id 更新用户信息
     * @param user
     */
    void updateById(TUser user);

    /**
     * @desc 查询部门相关用户信息
     * @param deptId
     * @return list
     */
    public List<TUser> queryUserByDeptId(Integer deptId);


}
