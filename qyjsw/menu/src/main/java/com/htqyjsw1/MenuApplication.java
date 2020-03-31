package com.htqyjsw1;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.htqyjsw.repository")
public class MenuApplication {
    public static void main(String[] args){
        SpringApplication.run(MenuApplication.class,args);
    }
}
