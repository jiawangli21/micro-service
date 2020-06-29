package com.equip.service;

import com.equip.config.FeignConfiguration;
import com.equip.entity.Result;
import com.equip.po.UserPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user",configuration = FeignConfiguration.class)
public interface UserService {

    /**
     * @desc 添加用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/addUser",method = RequestMethod.PUT)
    public Result addUser(UserPO user);

    /**
     * @desc  更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/updateUser",method = RequestMethod.POST)
    public Result updateUser(UserPO user);

    /**
     * @desc 根据用户id查询,包含用户所属部门，用户拥有的角色信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/findById",method = RequestMethod.GET)
    public Result findById(@RequestParam("userId") Long userId);

    /**
     * @desc 删除用户信息，根据用户编号
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/delete",method = RequestMethod.DELETE)
    public Result deleteUser(@RequestParam("userId") Long userId);

    /**
     * @desc 根据用户id查询用户拥有的角色-权限信息，自定义封装规则
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/findUserRight",method = RequestMethod.GET)
    public Result findUserRight(@RequestParam("userId") Long userId);

    /**
     * @desc 根据用户名称查询
     * @param userName
     * @return
     */
    @RequestMapping(value = "/user/findByName",method = RequestMethod.GET)
    public Result findByName(@RequestParam("userName") String userName);

    /**
     * @desc 分页查询
     * @return
     */
    @RequestMapping(value = "/user/findByPage/{page}/{pageSize}/",method = RequestMethod.GET)
    public Result queryByPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize);

    /**
     * @desc 统计用户数量
     * @return
     */
    @RequestMapping(value = "/user/count",method = RequestMethod.GET)
    public Result count();

    @RequestMapping(value = "/user/findAll",method = RequestMethod.GET)
    public Result findAll();

}
