package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserHandler;
import com.htqyjsw1.entity.TRight;
import com.htqyjsw1.repository.RightRepository;
import com.htqyjsw1.service.RightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RightServiceImpl implements RightService {

    private static Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private RightRepository rightRepository;


    @Override
    public List<TRight> queryByType(int type) {

        List<TRight> tRightList = rightRepository.queryByType(type);

        return tRightList;
    }

    @Override
    public String addRight(TRight tRight) {
        String result = "false";
        try {
            int id = rightRepository.insert(tRight);
            if(id > 0){
                logger.info("【添加权限信息成功！】，权限id："+id);
                result = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("【添加权限信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public String deleteRight(Long rightId) {
        String result = "false";
        try {
            //查看是否有权限角色关联的信息

            int id = rightRepository.delete(rightId);
            logger.info("【删除权限信息成功！】，权限id："+id);
            result = "success";
        }catch (Exception e){
            e.printStackTrace();
            logger.info("【删除页权限信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public void updateRight(TRight tRight) {
        try {
            rightRepository.update(tRight);
            logger.info("【更新页面信息成功！】" + tRight);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("【更新页面信息失败！】，异常："+ e);
        }
    }
}
