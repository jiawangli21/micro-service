package com.htqyjsw1.controller;

import com.htqyjsw1.entity.TRight;
import com.htqyjsw1.po.RolePO;
import com.htqyjsw1.service.RightService;
import com.htqyjsw1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 权限信息管理
 * @author lijiawang
 * @dtae 2020/4/2
 */
@RestController
@RequestMapping("/right")
public class RightController {


    @Autowired
    private RightService rightService;

    @PutMapping("/addRight")
    public String addRight(TRight tRight){
        String result = rightService.addRight(tRight);
        return result;
    }

    /**
     * @desc 删除权限信息
     * @param rightId
     * @return
     */
    @DeleteMapping("/deleteRight")
    public String deleteRight(Long rightId){
        String result = rightService.deleteRight(rightId);
        return result;
    }

    /**
     * @desc 更新权限信息
     * @param tRight
     * @return
     */
    @PostMapping("/updateRight")
    public String updateRight(TRight tRight){
        rightService.updateRight(tRight);
        return "success";
    }

    @GetMapping("/findByPage")
    public List<TRight> findByPage(){

        return null;
    }
}
