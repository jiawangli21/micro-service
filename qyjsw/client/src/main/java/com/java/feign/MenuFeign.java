package com.java.feign;


import com.java.entity.Menu;
import com.java.entity.MenuVV;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value="menu")
//声明调用的服务名称
public interface MenuFeign {

    @GetMapping("/menu/findAllVV")
    public MenuVV findAllVV();

    @GetMapping("/menu/findAll")
    public List<Menu> findAll();

   // @GetMapping("/menu/findAllVV")
   // public MenuVV

    //@GetMapping("/menu/findAllVo")
    //public menuVo findAllVo();
}
