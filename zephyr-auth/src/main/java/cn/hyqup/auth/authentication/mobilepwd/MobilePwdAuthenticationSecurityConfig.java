package cn.hyqup.auth.authentication.mobilepwd;

import cn.hyqup.auth.service.ZephyrUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;


@Component
public class MobilePwdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private ZephyrUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) {
        MobilePwdAuthenticationProvider provider = new MobilePwdAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        http.authenticationProvider(provider);

    }
}
