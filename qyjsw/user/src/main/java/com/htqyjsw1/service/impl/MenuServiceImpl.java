package com.htqyjsw1.service.impl;

import com.htqyjsw1.entity.*;
import com.htqyjsw1.repository.MenuRepository;
import com.htqyjsw1.service.MenuService;
import com.htqyjsw1.service.RoleService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import com.htqyjsw1.vo.PageVO;
import com.htqyjsw1.vo.UserRoleVO;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class MenuServiceImpl implements MenuService {

    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result addMenu(TMenu tMenu) {
        Result result = new Result(ResultStatusCode.OK);
         try {
             if (StringUtils.isNotEmpty(tMenu.getMenuName())){
                 if (StringUtils.isNotEmpty(tMenu.getMenuLevel().toString())){
                     menuRepository.insert(tMenu);
                     logger.info("【添加菜单成功！】，菜单："+tMenu);
                 }else {
                     result = new Result(400,"菜单级别为空");
                 }
             }else {
                 result = new Result(400,"菜单名称为空");
             }

         }catch (Exception e){
             result = new Result(ResultStatusCode.HTTP_ERROR_400);
             logger.info("【添加菜单失败！】，异常："+ e);
             e.printStackTrace();
         }
         return result;
    }

    @Override
    public Result deleteMenu(Long menuId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            //判断是否有父级菜单
            List<TMenu> tMenuList = menuRepository.findChildenMenus(menuId);
            if(tMenuList != null){
                logger.info("删除二级菜单");
                menuRepository.deleteByParentId(menuId);
            }
            int id = menuRepository.delete(menuId);
            logger.info("【删除菜单成功！】，菜单id："+id);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("【删除菜单失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result updateMenu(TMenu tMenu, HttpServletRequest request) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            String token = request.getHeader("Authorization");
            menuRepository.update(tMenu);
            //删除缓存
            if (token != null){
                Claims claims = TokenUtil.parseJWT(token);
                String tokenKey = claims.get("jti").toString();
                String[] s = tokenKey.split("_");
                long userId = Long.valueOf( s[1]);
                if (redisUtils.get("menu_"+ userId)!=null) {
                    redisUtils.del("menu_" + userId);
                    logger.info("【删除菜单缓存信息成功！】");
                }
            }else {
                result = new Result(ResultStatusCode.NOT_LOGIN);
            }
            logger.info("【更新菜单成功！】" + tMenu);
        }catch (Exception e){
            result =new Result(400,"更新菜单失败！");
            e.printStackTrace();
            logger.info("【更新菜单失败！】，异常："+ e);
        }

        return result;
    }

    @Override
    public List<TMenu> findAll() {
        List<TMenu> tMenuList = menuRepository.findAll();
        return tMenuList;
    }

    @Override
    public List<TMenu> selectByIds(List<Long> menuIdList) {
        List<TMenu> tMenuList = menuRepository.selectByIds(menuIdList);
        return tMenuList;
    }

    @Override
    public Result findUserMenu(Long userId) {
        Result result = new Result(ResultStatusCode.OK);
        try{
            List<TMenu> list = ( List<TMenu>)  redisUtils.get("menu_"+userId);
            if (list==null) {
                logger.info("【缓存为空，查询数据库】，userId = "+userId);
                List<TRole> tRoles = roleService.queryRoleByUserId(userId);
                list = new ArrayList<>();
                if (tRoles != null) {
                    //分别查询不同的权限信息  1:功能，2:菜单，3:页面

                    List<UserRoleVO> userRoleVOList = new ArrayList<>();
                    for (TRole tRole : tRoles) {
                        UserRoleVO userRoleVO = roleService.queryRole(tRole.getRoleId(), 2);
                        userRoleVOList.add(userRoleVO);
                    }
                    //封装数据
                    List<TMenu> menus = getMenus(userRoleVOList);

                    //获取顶级菜单
                    List<TMenu> firstMenu = new ArrayList<>();
                    for (TMenu menu : menus) {
                        if (menu.getMenuLevel() == 0) {
                            firstMenu.add(menu);
                        }
                    }
                    //查询二级菜单
                    for (TMenu menu : firstMenu) {
                        //查询所有子菜单
                        List<TMenu> childen = new ArrayList<>();
                        List<TMenu> childenMenus = menuRepository.findChildenMenus(menu.getMenuId());
                        if (childenMenus != null) {
                            for (TMenu tMenu : menus) {
                                for (TMenu childenMenu : childenMenus) {
                                    if (tMenu.getMenuId() == childenMenu.getMenuId()) {
                                        childen.add(tMenu);
                                    }
                                }
                            }
                            menu.setChildren(childen);
                        }

                        list.add(menu);
                    }
                }
                //放入缓存
                logger.info("【将用户关联的权限信息放入缓存】，查询结果:" + list);
                redisUtils.set("menu_" + userId.toString(), list, 60, TimeUnit.MINUTES);
            }
            result.setData(list);

        }catch (Exception e){
            logger.error("【查询用户菜单信息失败】，错误："+e);
            result = new Result(ResultStatusCode.HTTP_ERROR_400);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result findByPage(int page, int pageSize) {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【分页查询部门信息】，正在查找第 "+page+" 页部门信息");
        PageVO pageVO = new PageVO();
        //统计用户数量
        int count = menuRepository.count();
        //计算最大页数
        int maxPage = pageVO.countMaxPage(count, pageSize);
        int p = pageVO.countPage(page,maxPage);

        pageVO.setMaxPage(maxPage);
        pageVO.setPage(p);
        pageVO.setPageSize(pageSize);
        //开始位置
        int start = (p-1)*pageSize;
        logger.info("【分页查询开始位置】："+start+" 【结束位置】："+(start+pageSize));

        List<TMenu> list = menuRepository.findByPage(start,pageSize);
        pageVO.setMenuList(list);
        result.setData(pageVO);
        return result;
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
}
