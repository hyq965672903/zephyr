package cn.hyqup.auth.config;

import cn.hyqup.auth.jwt.JwtTokenEnhancer;
import cn.hyqup.auth.properties.base.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@Configuration
@ConditionalOnProperty(prefix = "zephyr.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
public class JwtConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
        return converter;
    }

    @Bean
    @ConditionalOnBean(TokenEnhancer.class)
    public TokenEnhancer jwtTokenEnhancer(){
        return new JwtTokenEnhancer();
    }
}
