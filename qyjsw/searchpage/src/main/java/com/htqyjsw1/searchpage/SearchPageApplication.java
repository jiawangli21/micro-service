package com.htqyjsw1.searchpage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.htqyjsw1.searchpage.dao")
public class SearchPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchPageApplication.class, args);
    }

}
