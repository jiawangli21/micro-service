package com.java.config;

import com.java.Exception.LoginException;
import com.java.Exception.RightException;
import com.java.entity.Result;
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
    public Result loginException(LoginException loginException){
        Result result = new Result(400,loginException.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(RightException.class)
    public Result rightException(RightException rightException){
        Result result = new Result(400,rightException.getMessage());
        return result;
    }

}
