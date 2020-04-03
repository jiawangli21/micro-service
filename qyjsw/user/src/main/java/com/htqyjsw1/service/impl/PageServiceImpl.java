package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.entity.TPage;
import com.htqyjsw1.repository.PageRepository;
import com.htqyjsw1.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc 页面业务处理
 * @date 2020/4.2
 */

@Service
public class PageServiceImpl implements PageService {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private PageRepository pageRepository;


    @Override
    public String addPage(TPage tPage) {
        String result = "false";
        try {
            int id = pageRepository.insert(tPage);
            if(id > 0){
                logger.info("【添加页面成功！】，页面id："+id);
                result = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【添加页面失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public String deletePage(Long pageId) {
        String result = "false";
        try {
            int id = pageRepository.delete(pageId);
            logger.info("【删除页面信息成功！】，页面id："+id);
            result = "success";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【删除页面信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public void updatePage(TPage tPage) {
        try {
            pageRepository.update(tPage);
            logger.info("【更新页面信息成功！】" + tPage);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【更新页面信息失败！】，异常："+ e);
        }
    }

    @Override
    public List<TPage> findAll() {

        List<TPage> tPageList = pageRepository.findAll();

        return tPageList;
    }

    @Override
    public List<TPage> selectByIds(List<Long> pageIdList) {
        List<TPage> tPageList = pageRepository.selectByIds(pageIdList);
        return tPageList;
    }
}
