package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserHandler;
import com.htqyjsw1.entity.*;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.repository.RoleRepository;
import com.htqyjsw1.service.*;
import com.htqyjsw1.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(UserHandler.class);

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

    @Override
    public String addRole(RolePO role) {

        String result = "false";
        TRole tRole = role.gettRole();
        List<TRoleRightRel> tRoleRightRels = role.gettRoleRightRelList();

        int id = roleRepository.insert(tRole);
        if (tRoleRightRels!=null) {
            //添加角色权限关联信息
            roleRepository.insertRoleRightRel(tRoleRightRels);
        }
        if(id > 0){
            logger.info("【添加角色信息成功！】，角色ID："+ id);
            result = "success";
        }
        return result;
    }

    @Override
    public void deleteRole(Integer roleId) {
        logger.info("【删除角色信息】，角色ID："+ roleId);
        //是否有关联的权限信息
         try{
             List<TRoleRightRel> tRoleRightRels = roleRepository.queryRoleRightRel(roleId);
             if(tRoleRightRels != null){
                 //删除角色权限关联信息
                 roleRepository.deleteRoleRightRel(roleId);
             }
             //删除角色信息
             roleRepository.deleteRole(roleId);
             logger.info("【删除角色信息成功！】，角色ID："+ roleId);
         }catch (Exception e){
             e.printStackTrace();
             logger.error("【删除角色信息失败！】，角色ID："+ roleId+" 【异常】"+ e);
         }

    }

    @Override
    public RoleRelVO queryRoleRel() {
        logger.info("【查询添加角色时关联的权限信息】");

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

        return roleRelVO;
    }

    @Override
    public RoleVO queryDetail(Integer roleId) {
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
        logger.info("【查询角色详细信息】，结果：" + roleVO);
        return roleVO;
    }


}
