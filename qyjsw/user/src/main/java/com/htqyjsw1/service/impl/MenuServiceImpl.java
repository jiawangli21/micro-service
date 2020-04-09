package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TMenu;
import com.htqyjsw1.repository.MenuRepository;
import com.htqyjsw1.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public Result addMenu(TMenu tMenu) {
        Result result = new Result(ResultStatusCode.OK);
         try {
             menuRepository.insert(tMenu);
             logger.info("【添加菜单成功！】，菜单："+tMenu);
         }catch (Exception e){
             e.printStackTrace();
             logger.info("【添加菜单失败！】，异常："+ e);
         }
         return result;
    }

    @Override
    public Result deleteMenu(Long menuId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            //判断是否有父级菜单
            List<TMenu> tMenuList = menuRepository.querySecondMenu(menuId);
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
    public Result updateMenu(TMenu tMenu) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            menuRepository.update(tMenu);
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
}
