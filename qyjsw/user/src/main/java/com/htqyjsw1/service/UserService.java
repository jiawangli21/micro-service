package com.htqyjsw1.service;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * @desc 添加用户信息
     * @param user
     * @return
     */
    public Result addUser(UserPO user);

    /**
     * @desc  更新用户信息
     * @param user
     * @return
     */
    public Result updateUser(UserPO user);

    /**
     * @desc 根据用户id查询,包含用户所属部门，用户拥有的角色信息
     * @param userId
     * @return
     */
    public Result findById(Long userId);

    /**
     * @desc 根据角色id，查询用户信息，返回用户集合list
     * @param roleId
     * @return
     */
    public List<TUser> queryUserByRoleId(Long roleId);

    /**
     * @desc 删除用户信息，根据用户编号
     * @param userId
     * @return
     */
    public Result deleteUser(Long userId);

    /**
     * @desc 根据用户id查询用户拥有的角色-权限信息，自定义封装规则
     * @param userId
     * @return
     */
    public Result findUserRight(Long userId);

    /**
     * @desc 根据用户名称查询
     * @param userName
     * @return
     */
    public Result findByName(String userName);

    /**
     * @desc 根据账号查询
     * @param userAcc
     * @return
     */
    public TUser findByUserAcc(String userAcc);

    /**
     * @desc 分页查询
     * @return
     */
    public Result queryByPage(int page, int pageSize);
}
