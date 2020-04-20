package com.htqyjsw1.service.impl;

import com.htqyjsw1.entity.*;
import com.htqyjsw1.repository.DeptRepository;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.DeptService;
import com.htqyjsw1.vo.DeptVO;
import com.htqyjsw1.vo.PageVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result addDept(TDept dept) {
        Result result = new Result(ResultStatusCode.OK);
        try{
            //校验字段是否为空
            if (StringUtils.isNotEmpty(dept.getDeptName()) && StringUtils.isNotEmpty(dept.getDeptHead()) &&
                    StringUtils.isNotEmpty(dept.getDeptAdd())){

                TDept tDept = deptRepository.findByName(dept.getDeptName());
                if(tDept != null){
                    result = new Result(400,"部门名称已存在！");
                }else{
                    deptRepository.insert(dept);
                    logger.info("【添加部门信息成功！】，部门内码："+dept.getDeptId());
                }
            }else{
                result = new Result(400,"部门名称或负责人或地址不能为空");
            }
        }catch (Exception e){
            result = new Result(400,"添加部门信息失败！");
            logger.error("【添加部门信息失败！】，错误："+e);
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public Result queryDetailById(Long deptId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            DeptVO deptVO = deptRepository.queryById(deptId);

            if(deptVO != null){
                List<TUser> userList = userRepository.queryUserByDeptId(deptId);
                deptVO.setUserList(userList);
            }
            result.setData(deptVO);
            logger.info("【查询部门详细信息】：部门内码 ："+deptId+",查询结果为：{}"+deptVO);
        }catch (Exception e){
            result = new Result(400,"查询部门详细信息失败！");
            logger.error("【查询部门详细信息失败！】，错误："+e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result queryByName(String deptName) {
        Result result = new Result(ResultStatusCode.OK);
        List<TDept> deptList = null;
        try{
            if (StringUtils.isNotEmpty(deptName)) {
                deptList = deptRepository.queryByName(deptName);
                if (deptList==null){
                    result.setMsg("没有找到该部门！");
                }
                logger.info("【根据部门名称查询部门信息】：部门名称 ：" + deptName + ",查询结果为：" + deptList);
            }else{
                result.setMsg("部门名称为空！");
            }

        }catch (Exception e){

        }
        result.setData(deptList);
        return result;
    }

    @Override
    public Result findByPage(int page,int pageSize) {
        Result result = new Result(ResultStatusCode.OK);
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
        result.setData(pageVO);
        return result;
    }

    @Override
    public Result updateById(TDept dept) {
        Result result = new Result(ResultStatusCode.OK);
       try{
           //校验字段是否为空
           if (StringUtils.isNotEmpty(dept.getDeptName()) && StringUtils.isNotEmpty(dept.getDeptHead()) &&
               StringUtils.isNotEmpty(dept.getDeptAdd())) {
               deptRepository.updateById(dept);
               logger.info("【更新部门信息成功！】，部门信息："+dept);
           }else{
               result = new Result(400,"部门名称或负责人或地址不能为空");
           }
       }catch (Exception e){
           logger.error("【更新部门信息失败】，部门信息："+dept);
           e.printStackTrace();
       }

        return result;
    }

    @Override
    public Result deleteDept(Long deptId) {
        Result result = new Result(ResultStatusCode.OK);
         try {
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
         }catch (Exception e){
             result =  new Result(400,"添加部门信息失败！");
             logger.error("【添加部门信息失败！】，错误："+e);
             e.printStackTrace();
         }
        return result;

    }

    @Override
    public List<TDept> findAll() {
        List<TDept> tDeptList = deptRepository.findAll();
        return tDeptList;
    }

    @Override
    public List<TDept> queryDeptByUserId(Long userId) {
        return deptRepository.queryDeptByUserId(userId);
    }
}
