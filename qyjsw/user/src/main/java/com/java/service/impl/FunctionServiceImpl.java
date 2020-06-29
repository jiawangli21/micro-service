package com.java.service.impl;

import com.java.entity.Result;
import com.java.entity.ResultStatusCode;
import com.java.entity.TFunction;
import com.java.po.FunctionPO;
import com.java.repository.FunctionRepository;
import com.java.service.FunctionService;
import com.java.utils.RedisUtils;
import com.java.utils.TokenUtil;
import com.java.vo.PageVO;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FunctionServiceImpl implements FunctionService {

    private static Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public List<TFunction> findAll() {
        Result result = new Result(ResultStatusCode.OK);
        List<TFunction> tFunctionList = functionRepository.findAll();

        return tFunctionList;
    }

    @Override
    public List<TFunction> selectByIds(List<Long> funIdList) {

        List<TFunction> tFunctionList = functionRepository.selectByIds(funIdList);
        return tFunctionList;
    }

    @Override
    public Result addFunction(FunctionPO functionPO) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            int id = functionRepository.insert(functionPO);
            if(id > 0){
                logger.info("【添加功能信息成功！】，功能id："+id);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【添加功能信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result deleteFunction(Long funId) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            int id = functionRepository.delete(funId);
            logger.info("【删除功能信息成功！】，功能id："+id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【删除功能信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result updateFunction(TFunction tFunction, HttpServletRequest request) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            String token = request.getHeader("Authorization");
            functionRepository.update(tFunction);
            //删除缓存
            if (token != null){
                Claims claims = TokenUtil.parseJWT(token);
                String tokenKey = claims.get("jti").toString();
                String[] s = tokenKey.split("_");
                long userId = Long.valueOf( s[1]);
                if(redisUtils.get("permission_"+ userId)!=null) {
                    redisUtils.del("permission_" + userId);
                }
                logger.info("【更新功能信息成功！】" + tFunction);
            }else {
                result = new Result(ResultStatusCode.NOT_LOGIN);
            }
        }catch (Exception e){
            result = new Result(400,"更新功能信息失败！");
            e.printStackTrace();
            logger.error("【更新功能信息失败！】，异常："+ e);
        }
        return result;
    }

    @Override
    public Result count() {
        Result result = new Result(ResultStatusCode.OK);
        int totalSize =  functionRepository.count();
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
        return result;
    }

    @Override
    public Result queryByPage(int page, int pageSize) {

        logger.info("【分页查询页面信息】，page = "+page+"   pageSize = "+pageSize);

        Result result = new Result(ResultStatusCode.OK);
        try {
            PageVO pageVO = new PageVO();
            //统计用户数量
            int count = functionRepository.count();
            int maxPage = pageVO.countMaxPage(count, pageSize);
            int p = pageVO.countPage(page, maxPage);

            pageVO.setMaxPage(maxPage);
            pageVO.setPage(p);
            pageVO.setPageSize(pageSize);
            int start = (p - 1) * pageSize;
            List<TFunction> tFunctionList= functionRepository.findByPage(start, pageSize);

            pageVO.setFunctionList(tFunctionList);

            result.setData(pageVO);
        }catch (Exception e){
            result = new Result(ResultStatusCode.HTTP_ERROR_400);
            logger.error("【分页查询功能信息失败！】，异常："+ e);
            e.printStackTrace();
        }
        return result;
    }
}
