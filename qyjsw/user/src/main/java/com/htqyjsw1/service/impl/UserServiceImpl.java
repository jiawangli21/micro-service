package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.entity.TRole;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.service.RoleService;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Override
    public String addUser(UserPO user) {
        String result = "false";
       try{
           userRepository.insert(user);
           Long userId = user.getUserId();
           List<Long> deptIds = user.getDeptIds();
           List<Long> roleIds = user.getRoleIds();
           if(deptIds.size()>0){
               //插入部门用户关联信息
               for (Long deptId : deptIds) {
                   userRepository.insertDeptUserRel(deptId,userId);
               }
           }
           if(roleIds.size()>0){
               //插入用户角色关联信息
               for (Long roleId : roleIds) {
                   userRepository.insertUserRoleRel(userId,roleId);
               }
           }
           logger.info("【添加用户信息成功！】，用户信息：" + user);
           result = "success";
       }catch (Exception e){
           logger.error("【添加用户信息失败！】，错误："+e);
           e.printStackTrace();
       }

        return result;
    }

    @Override
    public String updateUser(UserPO user) {
        String result = "false";
        try {
            Long userId = user.getUserId();
            userRepository.update(user);
            List<Long> deptIds = user.getDeptIds();
            List<Long> roleIds = user.getRoleIds();
            if(deptIds.size()>0){
                //插入部门用户关联信息
                for (Long deptId : deptIds) {
                    userRepository.insertDeptUserRel(deptId,userId);
                }
            }
            if(roleIds.size()>0){
                //插入用户角色关联信息
                for (Long roleId : roleIds) {
                    userRepository.insertUserRoleRel(userId,roleId);
                }
            }
            logger.info("【更新用户信息成功！】，用户信息：" + user);
            result = "success";
        }catch (Exception e){
            logger.error("【更新用户信息失败！】，错误："+e);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public UserVO findById(Long userId) {
        UserVO userVO = null ;
        try {
             userVO = userRepository.findById(userId);
            //查询用户所属部门
            List<TDept> hasDeptList = deptService.queryDeptByUserId(userId);
            List<TDept> allDeptList = deptService.findAll();
            //查询用户拥有的角色
            List<TRole> hasRoleList = roleService.queryRoleByUserId(userId);
            List<TRole> allRoleList = roleService.findAll();

            //数据封装
            userVO.setHasDeptList(hasDeptList);
            userVO.settDeptList(allDeptList);
            userVO.setHasRoleList(hasRoleList);
            userVO.settRoleList(allRoleList);
        }catch (Exception e){
            logger.error("【查询用户信息失败！】，错误："+e);
            e.printStackTrace();
        }

        return userVO;
    }

    @Override
    public String deleteUser(Long userId) {
        String result = "false";
        try{
            //是否有所属部门
            List<TDept> tDeptList = deptService.queryDeptByUserId(userId);
            if(tDeptList!=null){
                //删除部门用户关联信息
                userRepository.deleteDeptUserRel(userId);
            }
            //是否拥有角色
            List<TRole> tRoleList = roleService.queryRoleByUserId(userId);
            if(tRoleList!=null){
                //删除用户拥有角色关联信息
                userRepository.deleteUserRoleRel(userId);
            }
            int id = userRepository.delete(userId);
            result = "success";
        }catch (Exception e){
            logger.error("【删除用户信息失败！】，错误："+e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TUser> queryUserByRoleId(Long roleId) {
        return userRepository.queryUserByRoleId(roleId);
    }
}
