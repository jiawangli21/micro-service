package com.htqyjsw1.repository;


import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.entity.TPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageRepository {

    /**
     * @desc 添加页面信息
     * @param tPage
     * @return
     */
    public int insert(TPage tPage);

    /**
     * @desc 删除页面信息
     * @param pageId
     * @return
     */
    public int delete(Long pageId);

    /**
     * @desc 更新页面信息
     * @param tPage
     * @return
     */
    public void update(TPage tPage);

    /**
     * @desc 查询所有页面信息
     * @return List<TPage>
     */
    public List<TPage> findAll();
    /**
     * @desc 批量查询
     * @param pageIdList
     * @return
     */
    List<TPage> selectByIds(@Param("pageIdList") List<Long> pageIdList);
}
