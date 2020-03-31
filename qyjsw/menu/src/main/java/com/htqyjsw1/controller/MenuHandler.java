package com.htqyjsw1.controller;

import com.htqyjsw1.entity.MenuVV;
import com.htqyjsw1.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/index")
    public String index() {
        return this.port;
    }
    @GetMapping("/count")
     public int count(){
        return menuRepository.count();
    }
    @GetMapping("/findAll")
    public List<com.htqyjsw1.entity.Menu> findAll() {
        return menuRepository.findAll();
    }

    @GetMapping("/findById/{menu_id}")
    public com.htqyjsw1.entity.Menu findById(@PathVariable("menu_id") long menu_id){
        return menuRepository.findById(menu_id);
    }

    @GetMapping("/findAllVV")
    public MenuVV findAllVV(){
        MenuVV menuVV = new MenuVV();
        menuVV.setCode(0);
        menuVV.setStr("");
        menuVV.setCount(menuRepository.count());
        menuVV.setData(menuRepository.findAll());
        return menuVV;
    }
 //   @GetMapping("/count")
 //   public int count(){
 //       return menuRepository.count();
 //   }

}
