package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TUser;
import com.htqyjsw1.repository.UserRepository;
import com.htqyjsw1.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 用户信息管理类
 * @date 2020/3/31
 */
@RestController
@RequestMapping("/user")
public class UserHandler {

    private static Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index() {
        logger.info("当前正在访问的主机地址是："+address);
        return this.address;
    }

    /**
     * @desc 添加用户信息
     * @param user
     * @return
     */
    @PutMapping("/add")
    public String addUser(TUser user){
        int id = userRepository.insertSelective(user);
        if(id < 0){
            logger.info("添加用户信息失败"+ user);
            return "false";
        }else{
            logger.info("添加用户信息成功，用户ID:"+id);
            return "success";
        }
    }

    /**
     * @desc  删除用户信息
     * @return
     */
    @DeleteMapping("/delete")
    public String deleteUser(Integer userId){
        userRepository.deleteById(userId);

        return "success";
    }

    /**
     * @desc  更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    public String updateUser(TUser user){
        userRepository.updateById(user);
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

    /**
     * @desc 根据用户id查询
     * @param userId
     * @return
     */
     @GetMapping("/findById")
     public TUser findById(Integer userId){
         TUser user = userRepository.findById(userId);
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


}
