package htqyjsw1.controller;

import htqyjsw1.entity.Result;
import htqyjsw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping( value = "/invoker/findById", method = RequestMethod.GET )
    public Result getUsers(long userId){

        return userService.getUsers(userId);
    }
}
