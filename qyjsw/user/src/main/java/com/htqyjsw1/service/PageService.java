package com.htqyjsw1.service;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TPage;

import java.util.List;

public interface PageService {

    /**
     * @desc 添加页面信息
     * @param tPage
     * @return
     */
    public Result addPage(TPage tPage);

    /**
     * @desc 删除页面信息
     * @param pageId
     * @return
     */
    public Result deletePage(Long pageId);

    /**
     * @desc 更新页面信息
     * @param tPage
     * @return
     */
    public Result updatePage(TPage tPage);

    /**
     * @desc 查询所有页面信息
     * @return List<TPage>
     */
    public  List<TPage> findAll();


    /**
     * @desc 批量查询
     * @param pageIdList
     * @return
     */
    public List<TPage> selectByIds(List<Long> pageIdList);

    public Result queryByPage(int page, int pageSize);

    public Result count();
}
