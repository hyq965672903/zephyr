package cn.hyqup.auth.config;

import cn.hyqup.auth.authentication.mobilepwd.MobilePwdAuthenticationSecurityConfig;
import cn.hyqup.auth.constant.SecurityConstants;
import cn.hyqup.auth.properties.base.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MobilePwdAuthenticationSecurityConfig mobilePwdAuthenticationSecurityConfig;


    /**
     * AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                //授权服务器关闭basic认证
                .permitAll()
                .and()
                .logout()
                .logoutUrl(SecurityConstants.LOGOUT_URL)
//                .logoutSuccessHandler(new OauthLogoutSuccessHandler())
//                .addLogoutHandler(oauthLogoutHandler)
//                .clearAuthentication(true)
                .and()
                .apply(mobilePwdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

    }
}
