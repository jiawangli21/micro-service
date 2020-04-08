package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.utils.AESUtil;
import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import com.htqyjsw1.utils.ValidateCodeUtil;
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
    public String login(@ApiParam("账号") String userAcc,@ApiParam("密码") String password,HttpServletRequest request) {
        String token = null;
        try {
            if(userAcc != null && password != null){
                TUser user = userService.findByUserAcc(userAcc);
                if (user != null) {
                    if(password.equals(user.getUserPwd())){
                        String key = userAcc+":"+user.getUserId();
                        //生成token
                        token = TokenUtil.createJwtToken(key);
                        logger.info("【用户登录的令牌 token】："+token);

                        //将token存入redis，两小时失效

                        redisUtils.set(key,token,60*2, TimeUnit.MINUTES);
                    }else{
                        throw new Exception("密码错误！");
                    }
                }else{
                    throw new Exception("用户不存在！");
                }
            }else{
                throw new Exception("账号密码不能为空！");
            }
        }catch(Exception e){
            logger.error("【登录失败】，错误："+ e);
            e.printStackTrace();
        }
        return token;
    }

    @RequestMapping("/outLogin")
    @ApiOperation(value = "退出登录")
    public String outLogin(String userId){

        return null;
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
    public boolean checkVerify(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        try{
            //从session中获取随机数
            String inputStr = requestMap.get("inputStr").toString();
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            logger.error("验证码校验失败>>>   ", e);
            return false;
        }
    }

}
