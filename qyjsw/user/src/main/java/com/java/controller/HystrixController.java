package com.java.controller;

import com.java.entity.Result;
import com.java.entity.ResultStatusCode;
import com.java.service.DeptService;
import com.java.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
@Api(value="HystrixController",tags={"熔断措施"})
public class HystrixController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByName")
    @ApiOperation(value = " @HystrixCommand 指定回滚方法 ")
    @HystrixCommand(fallbackMethod = "")
    public Result findByName(@ApiParam("用户名称") String userName){
        Result  result = userService.findByName(userName);
        return result;
    }

    public Result findByNameFallback(){
        return new Result(ResultStatusCode.HTTP_ERROR_400);
    }
}
