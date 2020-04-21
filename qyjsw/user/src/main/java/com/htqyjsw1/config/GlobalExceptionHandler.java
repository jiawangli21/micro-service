package com.htqyjsw1.config;

import com.htqyjsw1.Exception.LoginException;
import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public Result OutLogin(LoginException loginException){
        Result result = new Result(400,loginException.getMessage());
        return result;
    }

}
