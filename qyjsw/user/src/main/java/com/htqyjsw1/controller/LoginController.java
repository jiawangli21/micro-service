package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.LoginPO;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import com.htqyjsw1.utils.ValidateCodeUtil;
import com.htqyjsw1.vo.LoginVO;
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
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
@Api(value = "用户登录管理", tags = {"登录管理接口"})
public class LoginController {

    public static final String RANDOMCODEKEY= "RANDOMVALIDATECODEKEY";

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils ;

    @PostMapping("/login")
    @ApiOperation("登录校验")
    public Result login(@RequestBody LoginPO loginPO, HttpSession session) {
        Result result = new Result(ResultStatusCode.OK);
        try {
            String random = (String) redisUtils.get(RANDOMCODEKEY);
            if (random == null) {
                return result = new Result(ResultStatusCode.INVALID_CAPTCHA);
            }
            if (loginPO.getVerificationCode()==null){
                return result = new Result(ResultStatusCode.INVALID_IS_NULL);
            }
            String code1 = random.toLowerCase();
            String code2 = loginPO.getVerificationCode().toLowerCase();
            if (!code1.equals(code2)) {
                return result = new Result(ResultStatusCode.INVALID_ERROR);
            }
            if(loginPO.getUserAcc() != null && loginPO.getPassword() != null){
                TUser user = userService.findByUserAcc(loginPO.getUserAcc());
                if (user != null) {
                    LoginVO loginVO = new LoginVO();
                    loginVO.settUser(user);
                    if(loginPO.getPassword().equals(user.getUserPwd())){
                        String key ="token_"+user.getUserId();
                        //生成token
                        String token = TokenUtil.createJwtToken(key);
                        logger.info("【用户登录的令牌 token】："+token);
                        loginVO.setToken(token);
                        //将token存入redis，两小时失效
                        redisUtils.set(key,token,60*24, TimeUnit.MINUTES);
                        result.setData(loginVO);
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
            result = new Result(ResultStatusCode.SHIRO_ERROR);
            logger.error("【登录异常】，错误："+ e);
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/outLogin")
    @ApiOperation(value = "退出登录")
    public Result outLogin(HttpServletRequest request){
        Result result = new Result(ResultStatusCode.OK);
        try {
            String token = request.getHeader("Authorization");
            if (token!=null){
                Claims claims = TokenUtil.parseJWT(token);
                String tokenKey = claims.get("jti").toString();
                //删除redis中的 token

                redisUtils.del(tokenKey);
            }
        }catch (Exception e){
            result = new Result(ResultStatusCode.HTTP_ERROR_300);
            e.printStackTrace();
        }

        return result;
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

            String randcode = validateCode.getRandcode(request, response);//输出验证码图片方法
            redisUtils.set(RANDOMCODEKEY,randcode,1,TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }

}
