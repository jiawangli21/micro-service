package com.htqyjsw1.service.impl;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TFunction;
import com.htqyjsw1.repository.FunctionRepository;
import com.htqyjsw1.service.FunctionService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
    public Result addFunction(TFunction tFunction) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            int id = functionRepository.insert(tFunction);
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
            //删除缓存
            if (token != null){
                functionRepository.update(tFunction);
                Claims claims = TokenUtil.parseJWT(token);
                String tokenKey = claims.get("jti").toString();
                String[] s = tokenKey.split("_");
                long userId = Long.valueOf( s[1]);
                redisUtils.del("permission_"+ userId);
                logger.info("【更新功能信息成功！】" + tFunction);
            }else {
                result = new Result(ResultStatusCode.KICK_OUT);
            }


        }catch (Exception e){
            result = new Result(400,"更新功能信息失败！");
            e.printStackTrace();
            logger.error("【更新功能信息失败！】，异常："+ e);
        }
        return result;
    }
}
