package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserController;
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

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

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
    public Result findById(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            UserVO userVO = userRepository.findById(userId);
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
        }catch (Exception e){
            result = new Result(ResultStatusCode.HTTP_ERROR_400);
            logger.error("【查询用户信息失败！】，错误："+e);
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
    public Result findUserRole(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
        UserRoleVO  roleVO = null;
        try {
            //获取缓存
            roleVO = (UserRoleVO) redisUtils.get(userId.toString());
            if (roleVO == null) {
                roleVO = new UserRoleVO();
                logger.info("【查询数据库】，userId:" + userId);
                //查询用户所拥有的角色
                List<TRole> tRoles = roleService.queryRoleByUserId(userId);
                roleVO.settRoles(tRoles);
                if (tRoles != null) {
                    //分别查询不同的权限信息  1:功能，2:菜单，3:页面
                    for (int type = 1; type < 4; type++) {
                        List<UserRoleVO> userRoleVOList = new ArrayList<>();
                        for (TRole tRole : tRoles) {
                            UserRoleVO userRoleVO = roleService.queryRole(tRole.getRoleId(), type);
                            userRoleVOList.add(userRoleVO);
                        }
                        //封装数据
                        if (type == 1) {
                            List<TFunction> functions = getFunctions(userRoleVOList);
                            roleVO.settFunctions(functions);
                        }
                        if (type == 2) {
                            List<TMenu> menus = getMenus(userRoleVOList);
                            roleVO.settMenus(menus);
                        }
                        if (type == 3) {
                            List<TPage> pages = getPages(userRoleVOList);
                            roleVO.settPages(pages);
                        }
                    }
                }
                //放入缓存
                logger.info("【将用户关联的权限信息放入缓存】，查询结果:" + roleVO);
                redisUtils.set(userId.toString(), roleVO, 60, TimeUnit.MINUTES);
            }
            result.setData(roleVO);
        }catch (Exception e){
            result = new Result(400,"获取数据失败!");
            logger.error("【获取数据失败!】，错误："+e);
            e.printStackTrace();
        }
        return result;
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


    /**
     * @desc 对 menu 去重
     * @param userRoleVOList
     */
    public  List<TMenu> getMenus(List<UserRoleVO>  userRoleVOList){
        List<TMenu> list = null;
        for (UserRoleVO userRoleVO : userRoleVOList) {
            if(userRoleVO != null && userRoleVO.gettMenus()!=null) {
                list = new ArrayList<>();
                for (TMenu tMenu : userRoleVO.gettMenus()) {
                    if (list.size() == 0) {
                        list.add(tMenu);
                    } else {
                        boolean bool = false;
                        for (TMenu menu : list) {
                            bool = menu.equals(tMenu);
                            if (bool) {
                                break;
                            }
                        }
                        if (!bool) {
                            list.add(tMenu);
                        }
                    }
                }
            }
        }
        return list;
    }



    @Override
    public Result findByName(String userName) {
        Result result = new Result(ResultStatusCode.OK);
        if (StringUtils.isNotEmpty(userName)){
            List<TUser> tUserList = userRepository.findByName(userName);
            if(tUserList==null){
                result =new Result(400,"没有找到该用户！");
            }
            result.setData(tUserList);
        }
        return result;
    }

    @Override
    public TUser findByUserAcc(String userAcc) {

        return userRepository.findByUserAcc(userAcc);
    }

    @Override
    public Result queryByPage(int page, int pageSize) {
        Result result = new Result(ResultStatusCode.OK);
        PageVO pageVO = new PageVO();
        //统计用户数量
        int count = userRepository.count();
        int maxPage = pageVO.countMaxPage(count, pageSize);
        int p = pageVO.countPage(page,maxPage);

        pageVO.setMaxPage(maxPage);
        pageVO.setPage(p);
        pageVO.setPageSize(pageSize);
        int start = (p-1)*pageSize;

        List<TUser> list = userRepository.findByPage(start,pageSize);
        pageVO.setUserlist(list);
        result.setData(pageVO);
        return result;
    }

    @Override
    public List<TUser> queryUserByRoleId(Long roleId) {
        return userRepository.queryUserByRoleId(roleId);
    }
}
