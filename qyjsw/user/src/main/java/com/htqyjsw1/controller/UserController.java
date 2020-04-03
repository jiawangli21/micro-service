package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.po.UserPO;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.service.UserService;
import com.htqyjsw1.vo.PageVO;
import com.htqyjsw1.vo.UserVO;
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

    @GetMapping("/index")
    public String index() {
        logger.info("当前正在访问的主机地址是："+address);
        return this.address;
    }

    @PutMapping("/addUser")
    public String addUser(@RequestBody UserPO user){
        String result = userService.addUser(user);
        return result;
    }


    @PostMapping("/updateUser")
    public String updateUser(@RequestBody UserPO user){
        String result = userService.updateUser(user);
        return result;
    }

    @DeleteMapping("/delete")
    public String deleteUser(Long userId){
        userService.deleteUser(userId);
        return "success";
    }



    /**
     * @desc 统计用户数量
     * @return
     */
    @GetMapping("/count")
    public int count(){
      return userRepository.count();}

     @GetMapping("/findAll")
     public List<TUser> findAll(){
       return userRepository.findAll();
     }


    /**
     * @desc 分页查询
     * @return
     */
    @GetMapping("/findByPage")
     public PageVO queryByPage(int page,int pageSize){
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
     public UserVO findById(Long userId){
         UserVO user = userService.findById(userId);
         return user;
     }

    /**
     * @desc 根据用户名称查询
     * @param userName
     * @return
     */
    @GetMapping("/findByName")
    public List<TUser> findByName(String userName){
        List<TUser> list = userRepository.findByName(userName);
        return list;
    }

    @GetMapping("/findUserRole")
    public Set<String> findUserRole(Long userId){
        return userService.findUserRole(userId);
    }


}
