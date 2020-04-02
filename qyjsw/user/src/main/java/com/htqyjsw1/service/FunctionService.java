package com.htqyjsw1.service;


import com.htqyjsw1.entity.TFunction;

import java.util.List;

public interface FunctionService {


    /**
     * @desc 查询所有功能信息
     * @return List<TFunction>
     */
    public List<TFunction> findAll();

    /**
     * @desc 批量查询
     * @param funIdList
     * @return
     */
    List<TFunction> selectByIds(List<Long> funIdList);

    /**
     * @desc 添加功能信息
     * @param tFunction
     * @return
     */
    public String addFunction(TFunction tFunction);

    /**
     * @desc 删除功能信息
     * @param funId
     * @return
     */
    public String deleteFunction(Long funId);

    /**
     * @desc 更新功能信息
     * @param tFunction
     * @return
     */
    public void updateFunction(TFunction tFunction);

}
