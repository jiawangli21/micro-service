package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import com.htqyjsw1.utils.ValidateCodeUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
@Api(value = "用户登录管理", tags = {"登录管理接口"})
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils ;

    @PostMapping("/login")
    @ApiOperation("登录校验")
    public Result login(@ApiParam("账号") String userAcc,@ApiParam("密码") String password,HttpServletRequest request) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            if(userAcc != null && password != null){
                TUser user = userService.findByUserAcc(userAcc);
                if (user != null) {
                    if(password.equals(user.getUserPwd())){
                        String key = userAcc+":"+user.getUserId();
                        //生成token
                        String token = TokenUtil.createJwtToken(key);
                        logger.info("【用户登录的令牌 token】："+token);
                        result.setData(token);
                        //将token存入redis，两小时失效
                        redisUtils.set(key,token,60*2, TimeUnit.MINUTES);
                    }else{
                        result = new Result(ResultStatusCode.ERROR_PWD);
                    }
                }else{
                    result = new Result(ResultStatusCode.NOT_EXIST_USER);
                }
            }else{
                result = new Result(ResultStatusCode.NOT_PARAM_USER_OR_ERROR_PWD);
            }
        }catch(Exception e){
            logger.error("【登录失败】，错误："+ e);
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/outLogin")
    @ApiOperation(value = "退出登录")
    public String outLogin(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token!=null){
            Claims claims = TokenUtil.parseJWT(token);
            String tokenKey = claims.get("jti").toString();
            //删除redis中的 token
            redisUtils.del(tokenKey);
        }

        return "success";
    }


    @GetMapping(value = "/getVerify")
    @ApiOperation(value = "生成验证码")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");  //设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");  //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            ValidateCodeUtil validateCode = new ValidateCodeUtil();

            validateCode.getRandcode(request,response);   //输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }



    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
    @ApiOperation(value = "校验验证码")
    public Result checkVerify(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        Result result = new Result(ResultStatusCode.OK);
        try{
            //从session中获取随机数
            String inputStr = requestMap.get("inputStr").toString();
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                result = new Result(ResultStatusCode.INVALID_CAPTCHA);
            }
            if (!random.equals(inputStr)) {
                return result = new Result(ResultStatusCode.INVALID_ERROR);
            }
        }catch (Exception e){
            logger.error("验证码校验失败>>>  错误：", e);
            e.printStackTrace();
        }
        return result;
    }

}
