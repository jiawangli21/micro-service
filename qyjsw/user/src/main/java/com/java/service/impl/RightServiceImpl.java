package com.java.service.impl;

import com.java.entity.Result;
import com.java.entity.ResultStatusCode;
import com.java.entity.TRight;
import com.java.repository.RightRepository;
import com.java.service.RightService;
import com.java.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RightServiceImpl implements RightService {

    private static Logger logger = LoggerFactory.getLogger(RightServiceImpl.class);

    @Autowired
    private RightRepository rightRepository;

    @Override
    public List<TRight> queryByType(int type) {
        List<TRight> tRightList = rightRepository.queryByType(type);
        return tRightList;
    }

    @Override
    public Result addRight(TRight tRight) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            int id = rightRepository.insert(tRight);
            if(id > 0){
                logger.info("【添加权限信息成功！】，权限id："+id);
            }
        }catch (Exception e){
            result = new Result(400,"添加权限信息失败！");
            e.printStackTrace();
            logger.error("【添加权限信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result deleteRight(Long rightId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            //查看是否有权限角色关联的信息

            int id = rightRepository.delete(rightId);
            logger.info("【删除权限信息成功！】，权限id："+id);

        }catch (Exception e){
            e.printStackTrace();
            logger.error("【删除页权限信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result updateRight(TRight tRight) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            rightRepository.update(tRight);
            logger.info("【更新权限信息成功！】" + tRight);
        }catch (Exception e){
            result = new Result(400,"更新权限信息失败！");
            e.printStackTrace();
            logger.error("【更新权限信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result findById(Long rightId) {
        Result result = new Result(ResultStatusCode.OK);
        TRight tRight = rightRepository.findById(rightId);
        result.setData(tRight);
        return result;
    }

    @Override
    public Result count() {
        Result result = new Result(ResultStatusCode.OK);
        int totalSize =  rightRepository.count();
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
        return result;
    }

    @Override
    public Result findByPage(int page, int pageSize) {
        Result result = new Result(ResultStatusCode.OK);
        logger.info("【分页查询权限信息】，正在查找第 "+page+" 页权限信息");
        PageVO pageVO = new PageVO();
        //统计用户数量
        int count = rightRepository.count();
        //计算最大页数
        int maxPage = pageVO.countMaxPage(count, pageSize);
        int p = pageVO.countPage(page,maxPage);

        pageVO.setMaxPage(maxPage);
        pageVO.setPage(p);
        pageVO.setPageSize(pageSize);
        //开始位置
        int start = (p-1) * pageSize;
        logger.info("【分页查询权限，开始位置】："+start+" 【结束位置】："+(start+pageSize));

        List<TRight> list = rightRepository.findByPage(start,pageSize);

        pageVO.setRightList(list);
        result.setData(pageVO);
        return result;
    }
}
