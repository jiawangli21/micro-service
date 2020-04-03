package com.htqyjsw1.service;

import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.vo.UserVO;

import java.util.List;

public interface UserService {

    /**
     * @desc 添加用户信息
     * @param user
     * @return
     */
    public String addUser(UserPO user);

    /**
     * @desc  更新用户信息
     * @param user
     * @return
     */
    public String updateUser(UserPO user);

    /**
     * @desc 根据用户id查询,包含用户所属部门，用户拥有的角色信息
     * @param userId
     * @return
     */
    public UserVO findById(Long userId);

    /**
     * @desc 根据角色id，查询用户信息，返回用户集合list
     * @param roleId
     * @return
     */
    public List<TUser> queryUserByRoleId(Long roleId);

    public String deleteUser(Long userId);
}
