package com.htqyjsw1.repository;


import com.htqyjsw1.entity.TFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionRepository {

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
     List<TFunction> selectByIds(@Param("funIdList") List<Long> funIdList);

    /**
     * @desc 添加功能信息
     * @param tFunction
     * @return
     */
    public int insert(TFunction tFunction);

    /**
     * @desc 删除功能信息
     * @param funId
     * @return
     */
    public int delete(Long funId);

    /**
     * @desc 更新功能信息
     * @param tFunction
     * @return
     */
    public void update(TFunction tFunction);
}