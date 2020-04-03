package com.htqyjsw1.service.impl;

import com.htqyjsw1.controller.UserController;
import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.repository.FunctionRepository;
import com.htqyjsw1.service.FunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FunctionServiceImpl implements FunctionService {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private FunctionRepository functionRepository;


    @Override
    public List<TFunction> findAll() {
        List<TFunction> tFunctionList = functionRepository.findAll();
        return tFunctionList;
    }

    @Override
    public List<TFunction> selectByIds(List<Long> funIdList) {

        List<TFunction> tFunctionList = functionRepository.selectByIds(funIdList);
        return tFunctionList;
    }

    @Override
    public String addFunction(TFunction tFunction) {
        String result = "false";
        try {
            int id = functionRepository.insert(tFunction);
            if(id > 0){
                logger.info("【添加功能信息成功！】，功能id："+id);
                result = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【添加功能信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public String deleteFunction(Long funId) {
        String result = "false";
        try {
            int id = functionRepository.delete(funId);
            logger.info("【删除功能信息成功！】，功能id："+id);
            result = "success";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【删除功能信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public void updateFunction(TFunction tFunction) {
        try {
            System.out.println(tFunction);
            functionRepository.update(tFunction);
            logger.info("【更新功能信息成功！】" + tFunction);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【更新功能信息失败！】，异常："+ e);
        }
    }
}
