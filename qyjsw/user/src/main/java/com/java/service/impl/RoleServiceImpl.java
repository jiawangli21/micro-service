package com.java.service.impl;

import com.java.entity.*;
import com.java.po.RolePO;
import com.java.repository.RoleRepository;
import com.java.service.*;
import com.java.utils.RedisUtils;
import com.java.utils.TokenUtil;
import com.java.vo.*;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RightService rightService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private PageService pageService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result addRole(RolePO role) {
        Result result = new Result(ResultStatusCode.OK);
       try{
           logger.info("【添加角色信息】，role：" + role);
           role.setCreateTime(new Date());
           List<TRoleRightRel> roleRightRels = role.gettRoleRightRelList();
           //插入角色
           roleRepository.insert(role);
           Long roleId = role.getRoleId();
           if (roleRightRels!=null){
               for (TRoleRightRel tRoleRightRel : roleRightRels) {
                   tRoleRightRel.setRoleId(roleId);
               }
               //添加角色权限关联信息
               roleRepository.insertRoleRightRel(roleRightRels);
           }
           logger.info("【添加角色信息成功！】，角色Id："+ roleId);

       }catch (Exception e){
           result = new Result(400,"添加角色信息失败！");
           logger.info("【添加角色信息失败！】，错误信息："+e);
           e.printStackTrace();
       }
        return result;
    }

    @Override
    public Result deleteRole(Long roleId) {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【删除角色信息】，角色ID："+ roleId);
         try{
             //是否有关联的权限信息
             List<TRoleRightRel> tRoleRightRels = roleRepository.queryRoleRightRel(roleId);
             if(tRoleRightRels != null){
                 //删除角色权限关联信息
                 roleRepository.deleteRoleRightRel(roleId);
             }
             //是否有有用户拥有该权限
             List<TUser> tUserList = userService.queryUserByRoleId(roleId);
             if(tUserList!=null){
                 //删除用户角色信息
                 roleRepository.deleteUserRoleRel(roleId);
             }
             //删除角色信息
             roleRepository.deleteRole(roleId);
             logger.info("【删除角色信息成功！】，角色ID："+ roleId);
         }catch (Exception e) {
             result = new Result(400,"删除角色信息失败！");
             e.printStackTrace();
             logger.error("【删除角色信息失败！】，角色ID：" + roleId + " 【异常】" + e);
         }
       return result;
    }

    @Override
    public Result queryAllRoleRel() {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【查询所有角色关联的权限信息】");

        RoleRelVO roleRelVO = new RoleRelVO();
        RightFunctionVO rightFunctionVO = new RightFunctionVO();
        RightPageVO rightPageVO = new RightPageVO();
        RightMenuVO rightMenuVO = new RightMenuVO();

        //查询 1:功能权限， 2:菜单权限， 3:页面权限, 4:资源权限
        List<TRight> rightList_1 = rightService.queryByType(1);
        List<TRight> rightList_2 = rightService.queryByType(2);
        List<TRight> rightList_3 = rightService.queryByType(3);

        rightFunctionVO.settRightList(rightList_1);
        rightMenuVO.settRightList(rightList_2);
        rightPageVO.settRightList(rightList_3);

        //资源信息查询
        List<TFunction> functionList = functionService.findAll();
        List<TPage> tPageList = pageService.findAll();
        List<TMenu> tMenuList = menuService.findAll();

        rightFunctionVO.settFunctionList(functionList);
        rightMenuVO.settMenuList(tMenuList);
        rightPageVO.settPageList(tPageList);

        roleRelVO.setRightFunctionVO(rightFunctionVO);
        roleRelVO.setRightMenuVO(rightMenuVO);
        roleRelVO.setRightPageVO(rightPageVO);
        result.setData(roleRelVO);
        return result;
    }

    @Override
    public Result queryDetail(Long roleId) {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【查询角色详细信息】，角色id：" + roleId);

        TRole tRole = roleRepository.queryById(roleId);

        RoleVO roleVO = new RoleVO();

        List<TRoleRightRel> tRoleRightRelList = roleRepository.queryRoleRightRel(roleId);
        List<Long> fun = new ArrayList<>();
        List<Long> menu = new ArrayList<>();
        List<Long> page = new ArrayList<>();
        if(tRoleRightRelList!= null){
            //分组查询
            for (TRoleRightRel roleRightRel : tRoleRightRelList) {
                if (roleRightRel.getEleType()==1){
                    fun.add(roleRightRel.getEleId());
                }else if(roleRightRel.getEleType()==2){
                    menu.add(roleRightRel.getEleId());
                }else if(roleRightRel.getEleType()==3){
                    page.add(roleRightRel.getEleId());
                }
            }
            if(fun.size()!=0){
                //查询功能信息
                List<TFunction> tFunctionList = functionService.selectByIds(fun);
                roleVO.settFunctionList(tFunctionList);
            }
            if(menu.size()!=0){
                //查询菜单信息
                List<TMenu> tMenuList = menuService.selectByIds(menu);
                roleVO.settMenuList(tMenuList);
            }
            if(page.size()!=0){
                //查询页面信息
                List<TPage> tPageList = pageService.selectByIds(page);
                roleVO.settPageList(tPageList);
            }
        }

        roleVO.setRoleId(tRole.getRoleId());
        roleVO.setRoleName(tRole.getRoleName());
        roleVO.setRoleCreater(tRole.getRoleCreater());
        roleVO.setCreateTime(tRole.getCreateTime());
        result.setData(roleVO);
        logger.info("【查询角色详细信息】，结果：" + roleVO);
        return result;
    }

    @Override
    public List<TRole> queryRoleByUserId(Long userId) {
        List<TRole> tRoleList = roleRepository.queryRoleByUserId(userId);

        return tRoleList;
    }

    @Override
    public List<TRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Result updateRole(RolePO rolePO, HttpServletRequest request) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            if (rolePO.getRoleName()!= null){
                roleRepository.update(rolePO);
            }
            List<TRoleRightRel> roleRightRels = rolePO.gettRoleRightRelList();
            Long roleId = rolePO.getRoleId();
//            System.out.println(roleRightRels);
            //清除角色关联的信息
            roleRepository.deleteRoleRightRel(roleId);
            if (roleRightRels != null && roleRightRels.size()>0){
                for (TRoleRightRel tRoleRightRel : roleRightRels) {
                    tRoleRightRel.setRoleId(roleId);
                }
                //添加角色权限关联信息
                roleRepository.insertRoleRightRel(roleRightRels);
            }

            String token = request.getHeader("Authorization");
            //删除缓存
            if (token != null){
                Claims claims = TokenUtil.parseJWT(token);
                String tokenKey = claims.get("jti").toString();
                String[] s = tokenKey.split("_");
                long  userId = Long.valueOf( s[1]);
                if (redisUtils.get("menu_"+ userId)!=null) {
                    redisUtils.del("menu_" + userId);
                    logger.info("【删除菜单缓存信息成功！】");
                }
                if(redisUtils.get("permission_"+ userId)!=null) {
                    redisUtils.del("permission_" + userId);
                }
                if (redisUtils.get("permissions_"+userId)!=null) {
                     redisUtils.del("permissions_"+userId);
                }
            }else {
                result = new Result(ResultStatusCode.NOT_LOGIN);
            }
        }catch (Exception e){
            result = new Result(400,"更新角色信息失败！");
            logger.error("【更新角色信息失败！】，错误：" + e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserRoleVO queryRoleRel(Long roleId, int type) throws  Exception {
        return roleRepository.queryRoleRel(roleId,type);
    }

    @Override
    public Result count() {
        Result result = new Result(ResultStatusCode.OK);
        int totalSize =  roleRepository.count();
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
        return result;
    }

    @Override
    public Result queryRoleRelById(Long roleId) {
        Result result = new Result(ResultStatusCode.OK);
        try{

            RoleVO roleVO = new RoleVO();

            TRole tRole = roleRepository.queryById(roleId);
            if (tRole!=null) {
                //查询角色权限关联表
                UserRoleVO funList = roleRepository.queryRoleRel(roleId, 1);
                UserRoleVO menuList = roleRepository.queryRoleRel(roleId, 2);
                UserRoleVO pageList = roleRepository.queryRoleRel(roleId, 3);
                if (funList != null) {
                    roleVO.settFunctionList(funList.gettFunctions());
                }
                if (menuList != null) {
                    roleVO.settMenuList(menuList.gettMenus());
                }
                if (pageList != null) {
                    roleVO.settPageList(pageList.gettPages());
                }
                roleVO.setRoleId(tRole.getRoleId());
                roleVO.setRoleName(tRole.getRoleName());
                roleVO.setRoleCreater(tRole.getRoleCreater());
                roleVO.setCreateTime(tRole.getCreateTime());
            }else{
                result = new Result(400,"该角色不存在！");
            }
            result.setData(roleVO);
        }catch (Exception e){
            logger.error("【根据id查询角色关联权限信息失败】，异常："+e);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Result findByPage(int page,int pageSize) {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【分页查询角色信息】，正在查找第 "+page+" 页角色信息");
        PageVO pageVO = new PageVO();
        //统计用户数量
        int count = roleRepository.count();
        //计算最大页数
        int maxPage = pageVO.countMaxPage(count, pageSize);
        int p = pageVO.countPage(page,maxPage);

        pageVO.setMaxPage(maxPage);
        pageVO.setPage(p);
        pageVO.setPageSize(pageSize);
        //开始位置
        int start = (p-1) * pageSize;
        logger.info("【分页查询角色，开始位置】："+start+" 【结束位置】："+(start+pageSize));

        List<TRole> list = roleRepository.findByPage(start,pageSize);

        pageVO.setRoleList(list);
        result.setData(pageVO);
        return result;
    }
}
