package micro.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("SSOUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        authenticationProvider.setUserDetailsService(userDetailsService);
        // 使用BCrypt进行密码的hash
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        // 禁止隐藏用户未找到异常
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    /**
     * @desc 配置用户可以直接访问哪些url，所有没被匹配器匹配到的url都需用户通过认证。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")
             .and()
             .authorizeRequests()
             .antMatchers("/oauth/**").authenticated()
            /*
                  loginPage("/login")指定了登录页的URL，并允许所有的用户(包括没认证的)访问登录页，formLogin().permitAll()方法允许所有用户访问这个URL。
             */
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll();


    }



}
