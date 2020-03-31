package com.htqyjsw1.controller;


import com.htqyjsw1.entity.Menu;
import com.htqyjsw1.entity.MenuVV;
import com.htqyjsw1.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public List<Menu> findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index=(page-1)*limit;
        return menuFeign.findAll();
    }


  @GetMapping("/findAllVV")
  @ResponseBody
  public MenuVV findAllVV(@RequestParam("page") int page, @RequestParam("limit") int limit){
     return menuFeign.findAllVV();
  }
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }
}
