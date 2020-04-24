package com.htqyjsw1.service.impl;

import com.htqyjsw1.entity.*;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.service.RoleService;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.vo.PageVO;
import com.htqyjsw1.vo.UserRoleVO;
import com.htqyjsw1.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result addUser(UserPO user) {
        Result result = new Result(ResultStatusCode.OK);
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
       }catch (Exception e){
           result = new Result(400,"添加用户信息失败！");
           logger.error("【添加用户信息失败！】，错误："+e);
           e.printStackTrace();
       }
        return result;
    }

    @Override
    public Result updateUser(UserPO user) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            Long userId = user.getUserId();
            userRepository.update(user);
            List<Long> deptIds = user.getDeptIds();
            List<Long> roleIds = user.getRoleIds();
            //查询原有数据
            Result result1 = this.findById(user.getUserId());
            UserVO userVO = (UserVO) result1.getData();
            List<TDept> hasDeptList = userVO.getHasDeptList();
            List<TRole> hasRoleList = userVO.getHasRoleList();

            //去重
            if(hasDeptList!=null) {
                for (TDept tDept : hasDeptList) {
                    if (deptIds.contains(tDept.getDeptId())) {
                        deptIds.remove(tDept.getDeptId());
                    }
                }
            }
            if (hasRoleList!=null){
                for (TRole tRole : hasRoleList) {
                    if (roleIds.contains(tRole.getRoleId())){
                        roleIds.remove(tRole.getRoleId());
                    }
                }
            }

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

        }catch (Exception e){
            result = new Result(400,"更新用户信息失败！");
            logger.error("【更新用户信息失败！】，错误："+e);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Result deleteUser(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
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

        }catch (Exception e){
            result = new Result(400,"删除用户信息失败！");
            logger.error("【删除用户信息失败！】，错误："+e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result findUserRight(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            //获取缓存
            List<String> permission  = (List<String>) redisUtils.get("permission_"+userId);

            if (permission == null) {
                permission = new ArrayList<>();
                logger.info("【缓存为空，查询数据库】，userId:" + userId);

                //查询用户所拥有的角色
                List<TRole> tRoles = roleService.queryRoleByUserId(userId);
                if (tRoles != null) {
                    //分别查询不同的权限信息  1:功能，2:菜单，3:页面
                    List<UserRoleVO> userRoleVOList = new ArrayList<>();
                    for (TRole tRole : tRoles) {
                        UserRoleVO userRoleVO = roleService.queryRoleRel(tRole.getRoleId(), 1);
                        userRoleVOList.add(userRoleVO);
                    }
                    if (userRoleVOList.size() > 0) {
                        List<TFunction> functions = getFunctions(userRoleVOList);
                        if (functions != null) {
                            for (TFunction function : functions) {
                                String perm = function.getFunSubsystemName() + ":" + function.getFunName() + ":" + function.getRightName();
                                permission.add(perm);
                            }
                        }
                    }
                }
                //放入缓存
                logger.info("【将用户关联的权限信息放入缓存】，查询结果:" + permission);
                redisUtils.set("permission_" + userId.toString(), permission, 60, TimeUnit.MINUTES);
            }
            result.setData(permission);
        }catch (Exception e){
            result = new Result(400,"获取数据失败!");
            logger.error("【获取数据失败!】，错误："+e);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @desc 对 function 去重
     * @param userRoleVOList
     */
    public  List<TFunction> getFunctions(List<UserRoleVO>  userRoleVOList){
        List<TFunction> list = null;
        for (UserRoleVO userRoleVO : userRoleVOList) {
            if ( userRoleVO!=null &&  userRoleVO.gettFunctions()!=null) {
                list = new ArrayList<>();
                for (TFunction tFunction : userRoleVO.gettFunctions()) {
                    if (list.size() == 0) {
                        list.add(tFunction);
                    } else {
                        boolean bool = false;
                        for (TFunction function : list) {
                            bool = function.equals(tFunction);
                            if (bool) {
                                break;
                            }
                        }
                        if (!bool) {
                            list.add(tFunction);
                        }
                    }
                }
            }
        }
        return list;
    }


    @Override
    public Result findById(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            UserVO userVO = userRepository.findById(userId);
            if (userVO!=null) {
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
                result.setData(userVO);
            }

        }catch (Exception e){
            result = new Result(ResultStatusCode.HTTP_ERROR_400);
            logger.error("【查询用户信息失败！】，错误："+e);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Result findByName(String userName) {
        Result result = new Result(ResultStatusCode.OK);
        List<TUser> tUserList  = null;
        try {
            if (StringUtils.isNotEmpty(userName)){
                logger.info("【根据用户名称查询用户信息】，userName ="+userName);
                tUserList = userRepository.findByName(userName);
            }else{
                result.setMsg("用户名为空！");
            }
            if (tUserList==null){
                //待修改
//                tUserList = userRepository.findAll();
                result.setMsg("没有找到匹配的用户！");
            }
            if(tUserList!=null){
                for (TUser tUser : tUserList) {
                    //查询用户所属部门
                    List<TDept> hasDeptList = deptService.queryDeptByUserId(tUser.getUserId());
                    //查询用户拥有的角色
                    List<TRole> hasRoleList = roleService.queryRoleByUserId(tUser.getUserId());

                    tUser.setDepts(hasDeptList);
                    tUser.setRoles(hasRoleList);
                }
            }
            result.setData(tUserList);
        }catch (Exception e){
            logger.error("【根据用户名称查询用户信息失败！】，错误："+e);
            result = new Result(ResultStatusCode.HTTP_ERROR_400);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TUser findByUserAcc(String userAcc) {

        return userRepository.findByUserAcc(userAcc);
    }

    @Override
    public Result queryByPage(int page, int pageSize) {
        logger.info("【分页查询用户信息】，page = "+page+"   pageSize = "+pageSize);

        Result result = new Result(ResultStatusCode.OK);
       try{
           PageVO pageVO = new PageVO();
           //统计用户数量
           int count = userRepository.count();
           int maxPage = pageVO.countMaxPage(count, pageSize);
           int p = pageVO.countPage(page,maxPage);

           pageVO.setMaxPage(maxPage);
           pageVO.setPage(p);
           pageVO.setPageSize(pageSize);
           int start = (p-1)*pageSize;
           List<TUser> tUsers = new ArrayList<>();
           List<TUser> list = userRepository.findByPage(start,pageSize);
           if (list!=null) {
               for (TUser tUser : list) {
                   List<TRole> tRoles = roleService.queryRoleByUserId(tUser.getUserId());
                   tUser.setRoles(tRoles);
                   List<TDept> tDepts = deptService.queryDeptByUserId(tUser.getUserId());
                   tUser.setDepts(tDepts);
                   tUsers.add(tUser);
               }
           }

           pageVO.setUserlist(tUsers);
           result.setData(pageVO);
       }catch (Exception e){
           logger.error("【分页查询用户信息失败】，错误："+ e);
           result = new Result(ResultStatusCode.HTTP_ERROR_400);
           e.printStackTrace();
       }
        return result;
    }

    @Override
    public List<String> queryAllowPaths(long userId) {
        List<String> paths = new LinkedList<>();
        try{
            //查询用户所拥有的角色
            List<TRole> tRoles = roleService.queryRoleByUserId(userId);

            if (tRoles != null) {
                List<UserRoleVO> userRoleVOList = new ArrayList<>();
                for (TRole tRole : tRoles) {
                    UserRoleVO userRoleVO = roleService.queryRoleRel(tRole.getRoleId(), 3);
                    userRoleVOList.add(userRoleVO);
                }
                if (userRoleVOList!=null) {
                    List<TPage> pages = getPages(userRoleVOList);
                    for (TPage page : pages) {
                        paths.add(page.getPageUrl());
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return paths;
    }

    @Override
    public List<TUser> queryUserByRoleId(Long roleId) {
        return userRepository.queryUserByRoleId(roleId);
    }

    /**
     * @desc 对 page 去重
     * @param userRoleVOList
     */
    public  List<TPage> getPages(List<UserRoleVO>  userRoleVOList){
        List<TPage> list = null;
        for (UserRoleVO userRoleVO : userRoleVOList) {
            if(userRoleVO != null && userRoleVO.gettPages()!= null) {
                list = new ArrayList<>();
                for (TPage tPage : userRoleVO.gettPages()) {
                    if (list.size() == 0) {
                        list.add(tPage);
                    } else {
                        boolean bool = false;
                        for (TPage page : list) {
                            bool = page.equals(tPage);
                            if (bool) {
                                break;
                            }
                        }
                        if (!bool) {
                            list.add(tPage);
                        }
                    }
                }
            }
        }
        return list;
    }
}
