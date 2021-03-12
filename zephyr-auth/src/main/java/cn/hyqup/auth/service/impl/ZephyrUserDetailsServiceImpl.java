package cn.hyqup.auth.service.impl;

import cn.hyqup.auth.domain.LoginAppUser;
import cn.hyqup.auth.service.ZephyrAccountStatusUserDetailsChecker;
import cn.hyqup.auth.service.ZephyrUserDetailsService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@Slf4j
@Service
public class ZephyrUserDetailsServiceImpl implements ZephyrUserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ZephyrAccountStatusUserDetailsChecker zephyrAccountStatusUserDetailsChecker;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String encode = passwordEncoder.encode("123");
//        if (1 == 1) {
//            throw new BadCredentialsException("密码错误，请重新输入");
//        }
        log.info(encode);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("admin");
        LoginAppUser loginAppUser = new LoginAppUser("17788888888", "张三", "17788888888", encode, grantedAuthorities);
        log.info("登陆用户当前状态：{}", JSON.toJSON(loginAppUser));
        zephyrAccountStatusUserDetailsChecker.check(loginAppUser);
        return loginAppUser;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        String encode = passwordEncoder.encode("123");
        log.info(encode);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("admin");
        LoginAppUser loginAppUser = new LoginAppUser("17788888888", "张三", "17788888888", encode, grantedAuthorities);
        zephyrAccountStatusUserDetailsChecker.check(loginAppUser);
        log.info("登陆用户当前状态：{}", JSON.toJSON(loginAppUser));
        return loginAppUser;
    }
}
