package com.htqyjsw1.controller;

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

import java.util.List;
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
    public String addUser(@RequestBody UserPO user){
        String result = userService.addUser(user);
        return result;
    }


    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户信息")
    public String updateUser(@RequestBody UserPO user){
        String result = userService.updateUser(user);
        return result;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户信息")
    public String deleteUser(@ApiParam("用户编号") Long userId){
        userService.deleteUser(userId);
        return "success";
    }



    /**
     * @desc 统计用户数量
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "统计用户数量")
    public int count(){
      return userRepository.count();}

     @GetMapping("/findAll")
     @ApiOperation(value = "查询所有")
     public List<TUser> findAll(){
       return userRepository.findAll();
     }


    /**
     * @desc 分页查询
     * @return
     */
    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查询")
     public PageVO queryByPage(@ApiParam("当前页数") int page,@ApiParam("每页显示的数据条数") int pageSize){
         PageVO pageVO = new PageVO();
         //统计用户数量
         int count = userRepository.count();
         int maxPage = pageVO.countMaxPage(count, pageSize);
         int p = pageVO.countPage(page,maxPage);

         pageVO.setMaxPage(maxPage);
         pageVO.setPage(p);
         pageVO.setPageSize(pageSize);
         int start = (p-1)*pageSize;

         List<TUser> list = userRepository.findByPage(start,pageSize);
         pageVO.setUserlist(list);

         return pageVO;
     }


     @GetMapping("/findById")
     @ApiOperation(value = "根据用户编号查询")
     public UserVO findById(@ApiParam("用户编号") Long userId){
         UserVO user = userService.findById(userId);
         return user;
     }

    /**
     * @desc 根据用户名称查询
     * @param userName
     * @return
     */
    @GetMapping("/findByName")
    @ApiOperation(value = "根据用户名称查询")
    public List<TUser> findByName(@ApiParam("用户名称") String userName){
        List<TUser> list = userRepository.findByName(userName);
        return list;
    }

    @GetMapping("/findUserRole")
    @ApiOperation(value = "获取用户所拥有的角色信息")
    public Set<String> findUserRole(@ApiParam("部门编号") Long userId){
        return userService.findUserRole(userId);
    }


}
