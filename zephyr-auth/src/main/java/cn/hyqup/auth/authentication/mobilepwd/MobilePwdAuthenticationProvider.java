package cn.hyqup.auth.authentication.mobilepwd;

import cn.hyqup.auth.service.ZephyrUserDetailsService;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description: 手机号密码
 */
@Setter
public class MobilePwdAuthenticationProvider implements AuthenticationProvider {
    private ZephyrUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobilePwdAuthenticationToken authenticationToken = (MobilePwdAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();
        UserDetails user = userDetailsService.loadUserByMobile(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("手机号或密码错误");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("手机号或密码错误");
        }
        MobilePwdAuthenticationToken authenticationResult = new MobilePwdAuthenticationToken(user, password, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobilePwdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
