package com.htqyjsw1.repository;


import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.po.FunctionPO;
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
     * @param functionPO
     * @return
     */
    public int insert(FunctionPO functionPO);

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

    /**
     * @desc 分页查询功能信息
     * @param start
     * @param pageSize
     * @return
     */
    public List<TFunction> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);

    public int count();
}
