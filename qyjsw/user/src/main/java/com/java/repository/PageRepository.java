package com.java.repository;


import com.java.entity.TPage;
import com.java.po.PagePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageRepository {

    /**
     * @desc 添加页面信息
     * @param pagePO
     * @return
     */
    public int insert(PagePO pagePO);

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

    /**
     * @desc 分页查询页面信息
     * @param start
     * @param pageSize
     * @return
     */
    public List<TPage> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);

    public int count();
}
