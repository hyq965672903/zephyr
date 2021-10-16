package cn.hyqup.auth.config;


import cn.hyqup.auth.authentication.granter.MobilePwdGranter;
import cn.hyqup.auth.exception.MyAuthExceptionEntryPoint;
import cn.hyqup.auth.properties.base.SecurityProperties;
import cn.hyqup.auth.service.ZephyrUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private ZephyrUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyAuthExceptionEntryPoint myAuthExceptionEntryPoint;

    @Autowired
    DataSource dataSource;

    /**
     * 配置令牌的存储
     * 访问端点配置。tokenStroe、tokenEnhancer服务
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(true);
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));

    }

    private List<TokenGranter> getTokenGranters(final AuthorizationServerEndpointsConfigurer endpoints, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        List<TokenGranter> tokenGranters = new ArrayList(Collections.singletonList(endpoints.getTokenGranter()));
        tokenGranters.add(new MobilePwdGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
        return tokenGranters;
    }


    /**
     * 用来配置客户端Id
     * clientDetailsService注入，决定clientDeatils信息的处理服务
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }


    private ClientDetailsService jdbcClientDetailsService() {
        // 直接构建JdbcClientDetailsService对象
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

//    @Autowired
//    private ClientDetailsService clientDetailsService() throws Exception {
//        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder().inMemory();
//        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
//            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
//                builder.withClient(client.getClientId())//Client 账号
//                        .secret(passwordEncoder.encode(client.getClientSecret()))// Client 密码
//                        .redirectUris(client.getRedirectUris()) // 配置回调地址，选填。
//                        .authorizedGrantTypes("refresh_token", "authorization_code", "mobile_password", "password") // 授权码模式
//                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds()) // token 有效期
//                        .refreshTokenValiditySeconds(2592000) // refreshToken 有效期30天
//                        .scopes("all"); // 可授权的 Scope
//            }
//        }
//        return builder.build();
//    }

    /**
     * @param security
     * @throws Exception tokenKeyAccess 对端点 /oauth/token_key 的访问权限,该端点用于获取加密密钥
     *                   checkTokenAccess 对端点 /oauth/check_token 的访问权限，该端点用于检查 token 是否有效
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        是否允许使用表单认证客户端模式，我这里保持统一都采用使用头部信息认证客户端，就不开启了
//        security
//                .allowFormAuthenticationForClients();AuthenticationSuccessEventListener
        security.authenticationEntryPoint(myAuthExceptionEntryPoint)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }
}
