package cn.hyqup.auth.service;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/9
 * @description:
 */
@Component
public class ZephyrAccountStatusUserDetailsChecker implements UserDetailsChecker {


    @Override
    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException("账户被锁定");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("账户被禁用");
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("账户已经过期");
        }

        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("客户端凭证过期");
        }
    }
}
