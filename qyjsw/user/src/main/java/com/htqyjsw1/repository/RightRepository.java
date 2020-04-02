package com.htqyjsw1.repository;

import com.htqyjsw1.entity.TRight;

import java.util.List;

public interface RightRepository {

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
    public int insert(TRight tRight);

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    public int delete(Long rightId);

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    public void update(TRight tRight);


}
