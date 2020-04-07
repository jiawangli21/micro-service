package com.htqyjsw1.controller;

import com.htqyjsw1.utils.RedisUtils;
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

@RestController
@RequestMapping("/login")
@Api(value = "用户登录管理", tags = {"登录管理接口"})
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisUtils redisUtils ;

    @PostMapping("/login")
    @ApiOperation("登录校验")
    public String login(@ApiParam("用户名") String userName,@ApiParam("密码") String password, HttpServletRequest request){
         String  token ="token";
        System.out.println(userName+"--"+password);
        HttpSession session = request.getSession();
        session.setAttribute("userName:password",userName+":"+password);
        redisUtils.set("userName",userName);
        return token;
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
            logger.error("验证码校验失败", e);
            return false;
        }
    }

}
