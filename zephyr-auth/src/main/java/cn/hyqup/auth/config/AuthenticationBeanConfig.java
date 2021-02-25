
package cn.hyqup.auth.config;

import cn.hyqup.auth.service.ZephyrAccountStatusUserDetailsChecker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthenticationBeanConfig {


    /**
     * 默认密码处理器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AccountStatusUserDetailsChecker.class)
    public ZephyrAccountStatusUserDetailsChecker accountStatusUserDetailsChecker() {
        return new ZephyrAccountStatusUserDetailsChecker();
    }

//    @Bean
//    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
//        return new MyAuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public MyAuthenctiationFailureHandler myAuthenctiationFailureHandler() {
//        return new MyAuthenctiationFailureHandler();
//    }
}
