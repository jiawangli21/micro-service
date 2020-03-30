package micro.service.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * @desc 用 @EnableAuthorizationServer 注解来配置OAuth2.0 授权服务机制，通过使用@Bean注解的几个方法一起来配置这个授权服务
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * @desc  ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     *                                         你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *                   clientId：（必须的）用来标识客户的Id。
     *                   secret：（需要值得信任的客户端）客户端安全码，如果有的话。
     *                   scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
     *                   authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
     *                   authorities：此客户端可以使用的权限（基于Spring Security authorities）。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 定义了两个客户端应用的通行证
        clients.inMemory()
                //（必须的）用来标识客户的Id
                .withClient("ben1")
                //（需要值得信任的客户端）客户端安全码，如果有的话。
                .secret(new BCryptPasswordEncoder().encode("123456"))
                  /*
                    authorization_code：授权码类型。
                    implicit：隐式授权类型。
                    password：资源所有者（即用户）密码类型。
                    client_credentials：客户端凭据（客户端ID以及Key）类型。
                    refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
                  */
                .authorizedGrantTypes("authorization_code", "refresh_token")
                /*
                    用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
                    例:  .scopes("all")
                */
                .scopes("all")

                //决定是否出现授权那一步骤
                .autoApprove(false)
                //验证通过之后回调地址
                .redirectUris("http://localhost:8086/login");
    }

    /**
     * @desc AuthorizationServerEndpointsConfigurer：配置授权类型（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     *                                                管理令牌（Managing Token）：
     *                                                AuthorizationServerTokenServices 接口定义了一些操作使得你可以对令牌进行一些必要的管理
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        /*   设置token的保存方式
            InMemoryTokenStore：将OAuth2AccessToken保存在内存(默认)
            JdbcTokenStore：    将OAuth2AccessToken保存在数据库
            JwkTokenStore：     将OAuth2AccessToken保存到JSON Web Key
            JwtTokenStore：     将OAuth2AccessToken保存到JSON Web Token
            RedisTokenStore：   将OAuth2AccessToken保存到Redis

            例： tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
        */

        tokenServices.setTokenStore(jwtTokenStore());

        tokenServices.setSupportRefreshToken(true);

        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());

        tokenServices.setTokenEnhancer(jwtAccessTokenConverter());

        // 设置token的有效时间 一天有效期
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));

        //配置令牌服务(token services)。
        endpoints.tokenServices(tokenServices);
    }

    /**
     * @desc AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("testKey");
        return converter;
    }
}
