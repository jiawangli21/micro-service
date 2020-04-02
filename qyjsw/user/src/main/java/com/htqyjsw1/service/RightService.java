package com.htqyjsw1.service;


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
    public String addRight(TRight tRight);

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    public String deleteRight(Long rightId);

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    public void updateRight(TRight tRight);
}
