package com.java.config;

import com.java.interceptor.LoginInterceptor;
import com.java.interceptor.RightInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * @desc 确保拦截器执行前，RedisUtil注入到spring容器中
     * @return
     */
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public RightInterceptor getRightInterceptor(){
        return new RightInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration regist1 = registry.addInterceptor(getLoginInterceptor());
//        //所要拦截的请求路径
//        regist1.addPathPatterns("/**/*");
//        //不需要拦截的资源
//        regist1.excludePathPatterns(
//                "/login/**",
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css",
//                "/*.jpg"                 //css静态资源
//        );
//        //忽略 swagger-ui.html
//        regist1.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");


//        InterceptorRegistration regist2 = registry.addInterceptor(getRightInterceptor());
//
//        regist2.addPathPatterns("/**/*");
//        regist2.excludePathPatterns(
//                "/login/**",
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css",
//                "/*.jpg"                 //css静态资源
//        );
//        //忽略 swagger-ui.html
//        regist2.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
