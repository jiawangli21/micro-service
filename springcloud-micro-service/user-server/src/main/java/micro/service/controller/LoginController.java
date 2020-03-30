package micro.service.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userLogin")
public class LoginController {

    /**
     * @description 用户登录验证
     * @param username
     * @param password
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(String username, String password, HttpServletRequest request) {
        try {
            Subject user = SecurityUtils.getSubject();
            System.out.println(username+"  "+password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                //用户登录验证
                user.login(token);
            } catch (LockedAccountException lae) {
                token.clear();
                request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
                return "/login";
            } catch (ExcessiveAttemptsException e) {
                token.clear();
                request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
                return "/login";
            } catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", "用户或密码不正确！");
                return "/login";
            }
            request.removeAttribute("error");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登录异常，请联系管理员！");
            return "/login";
        }
        return "redirect:index.html";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
         return "登录成功";
    }


    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(){
        return "登录页面";
    }

}
