package com.htqyjsw1.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderHandle {
    @Value("${server.port}")
    private String port;//="8010";
     //port=8010;
    @GetMapping("/index")
    private String index(){
        return "order的端口111："+this.port;
    }
}
