package com.htqyjsw1.service;


import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TFunction;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FunctionService {


    /**
     * @desc 查询所有功能信息
     * @return List<TFunction>
     */
    public  List<TFunction>  findAll();

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
    public Result addFunction(TFunction tFunction);

    /**
     * @desc 删除功能信息
     * @param funId
     * @return
     */
    public Result deleteFunction(Long funId);

    /**
     * @desc 更新功能信息
     * @param tFunction
     * @return
     */
    public Result updateFunction(TFunction tFunction, HttpServletRequest request);

    public Result queryByPage(int page, int pageSize);
}
