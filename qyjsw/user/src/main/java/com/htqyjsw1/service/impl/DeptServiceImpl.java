package com.htqyjsw1.service.impl;


import com.htqyjsw1.controller.UserHandler;
import com.htqyjsw1.entity.TDept;
import com.htqyjsw1.entity.TDeptUserRel;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.repository.DeptRepository;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.vo.DeptVO;
import com.htqyjsw1.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private static Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DeptVO queryDetailById(Integer deptId) {

        DeptVO deptVO = deptRepository.queryById(deptId);

        if(deptVO != null){
            List<TUser> userList = userRepository.queryUserByDeptId(deptId);
            deptVO.setUserList(userList);
        }
        logger.info("【查询部门详细信息】：部门内码 ："+deptId+",查询结果为："+deptVO);
        return deptVO;
    }

    @Override
    public List<TDept> queryByName(String deptName) {
        List<TDept> deptList = deptRepository.queryByName(deptName);
        logger.info("【根据部门名称查询部门信息】：部门名称 ："+deptName+",查询结果为："+deptList);
        return deptList;
    }

    @Override
    public PageVO findByPage(int page,int pageSize) {
        logger.info("【分页查询部门信息】，正在查找第 "+page+" 页部门信息");
        PageVO pageVO = new PageVO();
        //统计用户数量
        int count = deptRepository.count();
        //计算最大页数
        int maxPage = pageVO.countMaxPage(count, pageSize);
        int p = pageVO.countPage(page,maxPage);

        pageVO.setMaxPage(maxPage);
        pageVO.setPage(p);
        pageVO.setPageSize(pageSize);
        //开始位置
        int start = (p-1)*pageSize;
        logger.info("【分页查询开始位置】："+start+" 【结束位置】："+(start+pageSize));

        List<TDept> list = deptRepository.findByPage(start,pageSize);
        pageVO.setDeptList(list);

        return pageVO;
    }

    @Override
    public void updateById(TDept dept) {
        deptRepository.updateById(dept);
    }

    @Override
    public void deleteDept(Integer deptId) {
        logger.info("【删除部门信息】，部门内码："+deptId);
        //查询是否有该部门关联的用户信息
        List<TDeptUserRel> tDeptUserRelList = deptRepository.queryDeptUserRel(deptId);
        if (tDeptUserRelList!=null){
            //删除关联信息
            deptRepository.deleteDeptUserRel(deptId);
            //删除部门信息
            int id = deptRepository.deleteDept(deptId);
            logger.info("【完成删除部门信息和部门关联信息】，部门内码：" + id);
        }

    }
}
