package com.htqyjsw1.controller;

import com.htqyjsw1.entity.Result;
import com.htqyjsw1.entity.ResultStatusCode;
import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.vo.PageVO;
import com.htqyjsw1.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @desc 用户信息管理类
 * @date 2020/3/31
 */
@RestController
@RequestMapping("/user")
@Api(value="用户信息管理",tags={"用户信息管理操作接口"})
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/address")
    @ApiOperation(value = "获取主机地址")
    public String index() {
        logger.info("当前正在访问的主机地址是："+address);
        return this.address;
    }

    @PutMapping("/addUser")
    @ApiOperation(value = "添加用户信息")
    public Result addUser(@RequestBody UserPO user){
        Result result = userService.addUser(user);
        return result;
    }


    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户信息")
    public Result updateUser(@RequestBody UserPO user){
        Result result = userService.updateUser(user);
        return result;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户信息")
    public Result deleteUser(@ApiParam("用户编号") Long userId){
        Result result = userService.deleteUser(userId);
        return result;
    }

    /**
     * @desc 统计用户数量
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "统计用户数量")
    public Result count(){
        Result result = new Result(ResultStatusCode.OK);
        int totalSize =  userRepository.count();
        Map<String,Integer> map = new HashMap<>();
        map.put("totalSize",totalSize);
        result.setData(map);
      return result;
    }

     @GetMapping("/findAll")
     @ApiOperation(value = "查询所有")
     public Result findAll(){
        Result result = new Result(ResultStatusCode.OK);
        result.setData( userRepository.findAll());
       return result;
     }

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查询")
     public Result queryByPage(@ApiParam("当前页数") int page,@ApiParam("每页显示的数据条数") int pageSize){
        return userService.queryByPage(page,pageSize);
     }

     @GetMapping("/findById")
     @ApiOperation(value = "根据用户编号查询")
     public Result findById(@ApiParam("用户编号") Long userId){
         Result result = userService.findById(userId);
         return result;
     }

    @GetMapping("/findByName")
    @ApiOperation(value = "根据用户名称查询")
    public Result findByName(@ApiParam("用户名称") String userName){
        Result result = userService.findByName(userName);
        return result;
    }

    @GetMapping("/findUserRight")
    @ApiOperation(value = "获取用户所拥有的权限信息")
    public Result findUserRight(@ApiParam("用户编号") Long userId){

        return userService.findUserRight(userId);
    }


}
