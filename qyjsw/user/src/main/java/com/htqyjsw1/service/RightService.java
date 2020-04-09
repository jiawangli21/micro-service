package com.htqyjsw1.service;


import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.TRight;

import java.util.List;

public interface RightService {

    /**
     * @desc 通过权限类型查找
     * @param type
     * @return List<TRight>
     */
    public List<TRight> queryByType(int type);


    /**
     * @desc 添加权限信息
     * @param tRight
     * @return
     */
    public Result addRight(TRight tRight);

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    public Result deleteRight(Long rightId);

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    public Result updateRight(TRight tRight);

    /**
     * @desc 通过id查询权限
     * @param rightId
     * @return
     */
    public Result findById(Long rightId);

    public Result findByPage(int page, int pageSize);
}
